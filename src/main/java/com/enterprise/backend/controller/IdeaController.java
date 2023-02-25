package com.enterprise.backend.controller;

import com.enterprise.backend.DTO.IdeaRequest;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.service.ClientService;
import com.enterprise.backend.service.IdeaService;
import com.enterprise.backend.service.TopicServce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.println;


@RestController
@RequestMapping("/idea")
public class IdeaController {

    Logger logger = LoggerFactory.getLogger(IdeaController.class);
    @Autowired
    private IdeaService ideaService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TopicServce topicServce;

    @GetMapping()
    public List<Idea> getallidea() {
        return ideaService.getallidea();
    }

    @Transactional
    @PostMapping("/create")
    public Idea createtopic(@RequestBody IdeaRequest idea) {
        logger.info("create topic logger ==>" + idea.getName());
        Optional<Client> client = clientService.getClientByid(idea.getClient_id());
        Optional<Topic> topic = topicServce.gettopicbyid(idea.getTopic_id());
        Idea newIdea = new Idea();
        newIdea.setBody(idea.getBody());
        newIdea.setName(idea.getName());
        newIdea.setDate(idea.getDate());
        newIdea.setAttached_path(idea.getAttached_path());
        newIdea.setModify_date(idea.getModify_date());
        newIdea.setClient(client.get());
        newIdea.setTopic(topic.get());
        Idea created_idea = ideaService.createidea(newIdea);
        return newIdea;
    }
    @GetMapping("{id}")
    public List<Idea> getallidea(@PathVariable String id) {

        return ideaService.get(id);
    }

    @GetMapping("/gettotalview/{id}")
    public Integer getallidea(@PathVariable Long id) {

        return ideaService.gettotalview(id);
    }

    @GetMapping("/getupvote")
    public List<Idea> upvote (){

        return ideaService.getupvote();
    }

   }
