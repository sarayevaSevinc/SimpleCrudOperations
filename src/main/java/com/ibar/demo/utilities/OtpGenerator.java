package com.ibar.demo.utilities;

import java.util.Random;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OtpGenerator {

    public static String generateOTP(){
        String all = "1234567890";
        String otp="";
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            otp = otp.concat(String.valueOf(all.charAt(random.nextInt(9))));
        }
        log.info(otp);
        return otp;
    }
}
