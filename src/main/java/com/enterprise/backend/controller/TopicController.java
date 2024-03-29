package com.enterprise.backend.controller;

import com.enterprise.backend.DTO.Topic.TopicRequest;
import com.enterprise.backend.DTO.Topic.TopicWithMostFollowers;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.response.DeleteResponse;

import com.enterprise.backend.service.ExportDataService;
import com.enterprise.backend.service.TopicService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    @Autowired
    private TopicService topicServce;

    @Autowired
    private ExportDataService exportDataService;

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
        return topicServce.getTopDetail(id);
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

    @GetMapping("/top7followers")
    public List<TopicWithMostFollowers> topicWithMostFollowers (){

        return topicServce.top7follower();

    }

    //testing
    @GetMapping("/export/{id}")
    public void export(HttpServletResponse response,@PathVariable Long id) throws IOException {
        exportDataService.downloadtoCSV(response,id);
    }

}
