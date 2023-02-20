package com.enterprise.backend.controller;

import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.service.ClientService;
import com.enterprise.backend.service.TopicServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicServce topicServce;

    @GetMapping()
    public List<Topic> getallClient() {

        return topicServce.getalltopic();
    }

    @PostMapping("/create")
    public Topic createtopic(@RequestBody Topic topic) {
        return topicServce.createtopic(topic);
    }

}
