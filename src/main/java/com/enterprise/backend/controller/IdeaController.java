package com.enterprise.backend.controller;

import com.enterprise.backend.DTO.IdeaRequest;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.model.Idea_cate;
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


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/idea")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;


    @GetMapping()
    public List<Idea> getallidea() {
        return ideaService.getallidea();
    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public Idea createtopic(@RequestBody IdeaRequest idea) {
        return ideaService.createidea(idea);
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
    public List<Idea> upvote() {

        return ideaService.getupvote();
    }


    @PostMapping("/cate_idea")
    public String insertidea_catev2(@RequestParam long cate_id, @RequestParam long idea_id) {
        ideaService.insertv2(cate_id, idea_id);
        return "OK ADDED";
    }


}
