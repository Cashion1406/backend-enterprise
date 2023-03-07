package com.enterprise.backend.controller;

import com.enterprise.backend.DTO.Topic.TopicRequest;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.response.DeleteResponse;

import com.enterprise.backend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicServce;

    @GetMapping()
    public List<Topic> getAllTopic() {

        return topicServce.getAllTopic();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public Topic createTopic(@RequestBody TopicRequest topic) {
        return topicServce.createTopic(topic);
    }

    @GetMapping("/{id}")
    public Optional<Topic> getTopic(@PathVariable Long id) {
        return topicServce.getTopicById(id);
    }


    @PutMapping("/update")
    public Topic updateTopic(@RequestBody TopicRequest topic) {
        return topicServce.updateTopic(topic);
    }


    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteTopic(@PathVariable Long id) {

        return topicServce.deleteTopic(id);

    }

    @DeleteMapping("/softdelete/{id}")
    public String softDeleteTopic(@PathVariable Long id) {

        return topicServce.softDeleteTopic(id);
    }
}
