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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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
    @Scheduled(cron = "0 0 06,18 * * *")
    public void notificationIdeaClosureDate() {

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tmr = today.plusDays(3);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DateTimeFormatter remainTime = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy, hh:mm a");

        List<Topic> closureTopic = topicRepo.getclosureTopic(dateTimeFormatter.format(tmr), dateTimeFormatter.format(today));

        for (Topic topic : closureTopic) {

            LocalDateTime convert = LocalDateTime.parse(topic.getTopic_closure_date(), dateTimeFormatter);
            List<Client> clientList = clientRepo.getAllClientWithTopic(topic.getId());
            System.out.println("Topic closure date " + topic.getTopic_closure_date());
            for (Client client : clientList) {

                try {
                    mailService.sendMail(client.getEmail(), topic.getName() + "Topic closure date is due on " + topic.getTopic_closure_date(), client.getFirstname() + " " + client.getLastname() + " of department  " + client.getDepartment().getName() + " has incoming deadline at " + topic.getTopic_closure_date(), client, null, null, topic);
                    Notification notification = new Notification();
                    notification.setIsDelete(false);
                    notification.setStatus(false);
                    notification.setCreatedAt(dateTimeFormatter.format(today));
                    notification.setClient_id(client.getId());
                    notification.setContent("Topic closure date due on " + remainTime.format(convert) + ", please submit your idea on time ");
                    notification.setIsDelete(false);
                    notification.setStatus(true);
                    notification.setTitle("Idea Closure Date alert");
                    notificationRepo.save(notification);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }

    @Async
    @Scheduled(cron = " 0 0 06,18 * * *")
    public void notificationFinalClosureDate() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tmr = today.plusDays(4);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DateTimeFormatter remainTime = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy, hh:mm a");


        List<Topic> finalClosureTopic = topicRepo.getFinalClosureTopic(dateTimeFormatter.format(tmr), dateTimeFormatter.format(today));

        for (Topic topic : finalClosureTopic) {

            LocalDateTime convert = LocalDateTime.parse(topic.getFinal_closure_date(), dateTimeFormatter);

            System.out.println("Final closure date " + topic.getFinal_closure_date());

            List<Client> clientList = clientRepo.getAllClientWithTopic(topic.getId());
            for (Client client : clientList) {

                try {
                    mailService.sendMail(client.getEmail(), topic.getName() + " Final date is due on " + topic.getTopic_closure_date(), client.getFirstname() + " " + client.getLastname() + " of department  " + client.getDepartment().getName() + " has incoming deadline at " + topic.getTopic_closure_date(), client, null, null, topic);
                    Notification notification = new Notification();
                    notification.setIsDelete(false);
                    notification.setStatus(true);
                    notification.setCreatedAt(dateTimeFormatter.format(today));
                    notification.setClient_id(client.getId());
                    notification.setContent("Final date is due on  " + remainTime.format(convert) + " for topic : " + topic.getName());
                    notification.setTitle("Final Closure Date alert");
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
