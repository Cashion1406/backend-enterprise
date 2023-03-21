package com.enterprise.backend.service;

import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.model.Topic;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;


    @Async
    public void sendMail(String toEmail, String subject, String body, Client client, Client client_QA, Idea idea, Topic topic) throws MessagingException, IOException {

        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
        helper.setFrom("steven140602@gmail.com", "LAMIAN");

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter remainTime = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy, hh:mm a");


        if (topic != null) {

            if (subject.contains("closure")) {
                LocalDateTime convert = LocalDateTime.parse(topic.getTopic_closure_date(), dateTimeFormatter);
                ctx.setVariable("user_id", client.getId());
                ctx.setVariable("name", client.getFirstname() + " " + client.getLastname());
                ctx.setVariable("header2", "Closure Date");
                ctx.setVariable("context", body);
                ctx.setVariable("email", " : " + topic.getName());
                ctx.setVariable("date", " : " + remainTime.format(convert));
            } else {
                LocalDateTime convert = LocalDateTime.parse(topic.getFinal_closure_date(), dateTimeFormatter);
                ctx.setVariable("user_id", client.getId());
                ctx.setVariable("name", client.getFirstname() + " " + client.getLastname());
                ctx.setVariable("context", body);
                ctx.setVariable("header2", "Final Date ");
                ctx.setVariable("email", " : " + topic.getName());
                ctx.setVariable("date", " : " + remainTime.format(convert));
            }
            ctx.setVariable("header1", "Topic");
            ctx.setVariable("url", "http://localhost:3000/" + topic.getId());
            ctx.setVariable("button", "Check out this Topic");
            String html = templateEngine.process("emailTemplate", ctx);
            helper.setText(html, true);
        } else {
            ctx.setVariable("name", client_QA.getFirstname() + " " + client_QA.getLastname());
            ctx.setVariable("url", "http://localhost:3000/" + idea.getId());
            ctx.setVariable("context", body);
            ctx.setVariable("header1", "Client email address");
            ctx.setVariable("header2", "Idea created date");
            ctx.setVariable("button", "Check out this Idea");
            ctx.setVariable("email", " : " + client.getEmail());
            ctx.setVariable("date", " : " + idea.getDate());
            String html = templateEngine.process("emailTemplate", ctx);
            helper.setText(html, true);

        }
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.addInline("logo", new ClassPathResource("/logo.png"));
        mailSender.send(mailMessage);
    }


}
