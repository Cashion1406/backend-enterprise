package com.enterprise.backend.ScheduleTask;


import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Notification;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.repo.ClientRepo;
import com.enterprise.backend.repo.NotificationRepo;
import com.enterprise.backend.repo.TopicRepo;
import com.enterprise.backend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SceduleEx {


    @Autowired
    private TopicRepo topicRepo;

    @Autowired
    private ClientRepo clientRepo;


    @Autowired
    private MailService mailService;

    @Autowired
    private NotificationRepo notificationRepo;

    // "0 0 * * *" = At 12:00 AM daily//
    // "0 0 3,15 * * * = At 03:00 AM and 03:00 PM daily
    // "0 0 0/12 * * *" = Every 12 hours//
    // "0/30 0/1 * * * *" = Every 30 second
    @Async
    @Scheduled(cron = "0 0 0/12 * * *")
    public void test() {


        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tmr = today.plusDays(1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Topic> list = topicRepo.getclosureTopic(dateTimeFormatter.format(tmr));

        System.out.println(dateTimeFormatter.format(tmr));
        System.out.println(dateTimeFormatter.format(today));
        for (Topic topic : list) {

            List<Client> clientList = clientRepo.getAllClientWithTopic(topic.getId());

            for (Client client : clientList) {

                try {
                    mailService.sendMail(client.getEmail(), topic.getName() + " closure date is due on " + topic.getTopic_closure_date(), client.getFirstname() +" "+ client.getLastname() + " of department  "+ client.getDepartment().getName() + " has incoming deadline at " + topic.getTopic_closure_date());
                    Notification notification = new Notification();
                    notification.setIsDelete(false);
                    notification.setStatus(false);
                    notification.setCreatedAt(dateTimeFormatter.format(today));
                    notification.setClient_id(client.getId());
                    notification.setContent(" You have up coming activities on topic " + topic.getName());
                    notification.setIsDelete(false);
                    notification.setStatus(true);
                    notificationRepo.save(notification);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
