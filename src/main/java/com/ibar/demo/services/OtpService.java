package com.ibar.demo.services;

import com.ibar.demo.config.securityConfig.JwtTokenUtil;
import com.ibar.demo.controllers.dto.JwtResponseDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.exceptions.OtpVerificationException;
import com.ibar.demo.model.OTP;
import com.ibar.demo.model.Token;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.OTPRepository;
import com.ibar.demo.repositories.RedisOtpRepository;
import com.ibar.demo.repositories.TokenRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.OtpGenerator;
import com.ibar.demo.utilities.Translator;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class OtpService {
    private final RedisOtpRepository redisOtpRepository;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final ErrorMapper errorMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final TokenRepository tokenRepository;
    private  final OTPRepository otpRepository;

    public OtpService(RedisOtpRepository redisOtpRepository, UserRepository userRepository, MailService mailService,
                      Translator translator, JwtTokenUtil jwtTokenUtil, TokenRepository tokenRepository,
                      OTPRepository otpRepository) {
        this.redisOtpRepository = redisOtpRepository;
        this.mailService = mailService;
        this.errorMapper = new ErrorMapper(translator);
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenRepository = tokenRepository;
        this.otpRepository = otpRepository;
    }

    public void sendOtp(User user) {
        String otpString = OtpGenerator.generateOTP();
        OTP otp = OTP.builder()
                .user_id(user.getId())
                .otp(otpString)
                .build();
        OTP saved = otpRepository.save(otp);
        redisOtpRepository.addOtp(user.getId(), saved);
        mailService.sendEmail(user.getEmail(), otpString);

    }

    public JwtResponseDTO verifyOTP(long userId, String otp) {
        Optional<User> userById = userRepository.getUserById(userId);
        if (!userById.isPresent()) {
            throw new AccountNotFoundException(errorMapper.getUserNotFoundByIdError());
        }
        OTP expectedOtp = redisOtpRepository.getOtp(userId);
        log.info(expectedOtp.getUser_id());
        log.info(expectedOtp.getOtp());
        if (expectedOtp == null || !expectedOtp.getOtp().equals(otp)) {
            throw new OtpVerificationException(errorMapper.getOtpVerificationError());
        }
        String token = jwtTokenUtil.generateToken(userById.get());
        expectedOtp.setExpired(1);
        otpRepository.save(expectedOtp);

        tokenRepository.save(Token.builder()
                .user_id(userId)
                .token(token)
                .build());

        return JwtResponseDTO.builder()
                .jwttoken(token)
                .build();
    }

}
