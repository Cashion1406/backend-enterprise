package com.enterprise.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    @Async
    public void sendMail (String toEmail, String subject, String body){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("steven140602@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);

        mailSender.send(mailMessage);
    }


}
