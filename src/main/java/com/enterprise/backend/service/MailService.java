package com.enterprise.backend.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    @Async
    public void sendMail (String toEmail, String subject, String body) throws MessagingException, UnsupportedEncodingException {

        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
        helper.setFrom("steven140602@gmail.com","ADMIN");
        helper.setTo(toEmail);
        helper.setText(body);
        helper.setSubject(subject);
        mailSender.send(mailMessage);
    }


}
