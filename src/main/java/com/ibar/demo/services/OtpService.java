package com.ibar.demo.services;

import com.ibar.demo.controllers.dto.OTPRequestDTO;
import com.ibar.demo.exceptions.OtpVerificationException;
import com.ibar.demo.model.OTP;
import com.ibar.demo.model.User;
import com.ibar.demo.repositories.OTPRepository;
import com.ibar.demo.repositories.RedisOtpRepository;
import com.ibar.demo.utilities.ErrorMapper;
import com.ibar.demo.utilities.OtpGenerator;
import com.ibar.demo.utilities.Translator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class OtpService {
    private final RedisOtpRepository redisOtpRepository;
    private final MailService mailService;
    private final ErrorMapper errorMapper;
    private final OTPRepository otpRepository;

    public OtpService(RedisOtpRepository redisOtpRepository, MailService mailService,
                      Translator translator,
                      OTPRepository otpRepository) {
        this.redisOtpRepository = redisOtpRepository;
        this.mailService = mailService;
        this.errorMapper = new ErrorMapper(translator);
        this.otpRepository = otpRepository;
    }

    public OTP sendOtp(OTPRequestDTO otpRequestDTO) {
        String otpString = OtpGenerator.generateOTP();
        OTP otp = OTP.builder()
                .user_id(otpRequestDTO.getUser_id())
                .otp(otpString)
                .build();
        OTP saved = otpRepository.save(otp);
        redisOtpRepository.addOtp(otpRequestDTO.getUser_id(), saved);
        mailService.sendEmail(otpRequestDTO.getEmail(), otpString);
        return saved;
    }

    public boolean verifyOTP(long userid, String otp) {
        OTP expectedOtp = redisOtpRepository.getOtp(userid);
        log.info(expectedOtp.getUser_id());
        log.info(expectedOtp.getOtp());
        if (expectedOtp == null || !expectedOtp.getOtp().equals(otp)) {
            throw new OtpVerificationException(errorMapper.getOtpVerificationError());
        }
        expectedOtp.setExpired(1);
        otpRepository.save(expectedOtp);
        return true;
    }

}
