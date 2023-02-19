package com.enterprise.backend.service;

import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.repo.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServce {

    @Autowired
    private TopicRepo topicRepo;


    public List<Topic> getalltopic() {

        return topicRepo.findAll();
    }

    public Topic createtopic(Topic topic) {

        return topicRepo.saveAndFlush(topic);
    }


}
