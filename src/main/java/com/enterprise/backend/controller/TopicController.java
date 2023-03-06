package com.enterprise.backend.controller;

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
    public List<Topic> getallClient() {

        return topicServce.getalltopic();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
        public Topic createtopic(@RequestBody Topic topic) {
        return topicServce.createtopic(topic);
    }

    @GetMapping("/{id}")
    public Optional<Topic> gettopic(@PathVariable Long id) {
        return topicServce.gettopicbyid(id);
    }


    @PutMapping("/update")
    public Topic updatetopic (@RequestBody Topic topic){
        return topicServce.updateTopic(topic);
    }


    @DeleteMapping("/delete/{id}")
    public DeleteResponse delete (@PathVariable Long id){

        return topicServce.deleteTopic(id);

    }

    @DeleteMapping("/softdelete/{id}")
    public String softdelete (@PathVariable Long id){

        return topicServce.softdelete(id);
    }
}
