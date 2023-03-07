package com.enterprise.backend.controller;

import com.enterprise.backend.DTO.Topic.TopicRequest;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.service.TopicServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicServce topicServce;

    @GetMapping()
    public List<Topic> getAllTopic() {

        return topicServce.getalltopic();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public Topic createTopic(@RequestBody TopicRequest topic) {
        return topicServce.createtopic(topic);
    }

    @GetMapping("/{id}")
    public Optional<Topic> getTopic(@PathVariable Long id) {
        return topicServce.gettopicbyid(id);
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
    public String softdeleteTopic(@PathVariable Long id) {

        return topicServce.softdelete(id);
    }
}
