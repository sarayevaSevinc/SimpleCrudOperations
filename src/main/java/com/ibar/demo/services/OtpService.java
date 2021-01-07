package com.ibar.demo.services;

import com.ibar.demo.config.securityConfig.JwtTokenUtil;
import com.ibar.demo.controllers.dto.JwtResponseDTO;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.exceptions.OtpVerificationException;
import com.ibar.demo.model.Token;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.RedisOtpRepository;
import com.ibar.demo.repositories.TokenRepository;
import com.ibar.demo.repositories.UserRepository;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.OtpGenerator;
import com.ibar.demo.utilities.Translator;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
    private final RedisOtpRepository redisOtpRepository;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final Translator translator;
    private final ErrorMapper errorMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final TokenRepository tokenRepository;

    public OtpService(RedisOtpRepository redisOtpRepository, UserRepository userRepository, MailService mailService,
                      Translator translator, JwtTokenUtil jwtTokenUtil, TokenRepository tokenRepository) {
        this.redisOtpRepository = redisOtpRepository;
        this.mailService = mailService;
        this.translator = translator;
        this.errorMapper = new ErrorMapper(translator);
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenRepository = tokenRepository;
    }

    public void sendOtp(User user) {
        String otp = OtpGenerator.generateOTP();
        redisOtpRepository.addOtp(user.getId(), otp);
        mailService.sendEmail(user.getEmail(), otp);

    }

    public JwtResponseDTO verifyOTP(long userId, String otp) {
        Optional<User> userById = userRepository.getUserById(userId);
        if (!userById.isPresent()) {
            throw new AccountNotFoundException(errorMapper.getUserNotFoundByIdError());
        }
        String expectedOtp = redisOtpRepository.getOtp(userId);
        if (expectedOtp == null || expectedOtp.isEmpty() || !expectedOtp.equals(otp)) {
            throw new OtpVerificationException(errorMapper.getOtpVerificationError());
        }
        String token = jwtTokenUtil.generateToken(userById.get());

        tokenRepository.save(Token.builder()
                .user_id(userId)
                .token(token)
                .build());

        return JwtResponseDTO.builder()
                .jwttoken(token)
                .build();
    }

}
