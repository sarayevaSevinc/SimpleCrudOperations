package com.ibar.demo.services;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email_address, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("UrlShortenerAppBySevinc");
        message.setTo(email_address);
        message.setSubject("One Time Password");
        message.setText("Hi!  Welcome to Gmail of Future " +
                "This is your one time password: ".concat(otp)
                .concat("\n Sincerely, !"));

        mailSender.send(message);
    }
}
