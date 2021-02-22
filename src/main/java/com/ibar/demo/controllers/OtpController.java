package com.ibar.demo.controllers;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

import com.ibar.demo.constants.StaticVariable;
import com.ibar.demo.controllers.dto.OTPRequestDTO;
import com.ibar.demo.model.OTP;
import com.ibar.demo.model.User;
import com.ibar.demo.services.OtpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@Validated
@RequestMapping(path = {"/otp"})
@Api(value = "Simple Crud Api")
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @ApiOperation(value = "user", notes = "update User")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/create-otp")
    public ResponseEntity<OTP> createOtp(@RequestBody OTPRequestDTO otpRequestDTO) {

        log.info("updating user ......");
        return new ResponseEntity<>(otpService.sendOtp(otpRequestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "user", notes = "verify Otp")
    @ApiResponses(value = {@ApiResponse(code = 200, message = StaticVariable.MESSAGE, response = User.class)})
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(
            @RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az") String lang,
            @RequestParam long userid,
            @RequestParam String otp) {

        StaticVariable.lang = lang;
        log.info("updating user ......");

        return new ResponseEntity<>(otpService.verifyOTP(userid, otp), HttpStatus.OK);
    }
}
