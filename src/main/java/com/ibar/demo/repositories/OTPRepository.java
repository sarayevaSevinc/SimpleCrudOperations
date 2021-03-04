package com.ibar.demo.repositories;


import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

import com.ibar.demo.controllers.dto.OTPDTO;
import com.ibar.demo.model.OTP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "otp-service", url = "http://localhost:8090/otp")
public interface OTPRepository {
    @RequestMapping(method = RequestMethod.POST, value = "/create-otp")
    OTP createOtp(@RequestBody OTPDTO OTPDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/verify-otp")
    boolean verifyOtp( @RequestHeader(value = ACCEPT_LANGUAGE, defaultValue = "az") String lang,
                   @RequestParam long userid,
                   @RequestParam String otp);
}
