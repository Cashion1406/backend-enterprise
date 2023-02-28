package com.enterprise.backend.controller;

import com.enterprise.backend.DTO.CommentRequest;
import com.enterprise.backend.DTO.IdeaRequest;
import com.enterprise.backend.DTO.Idea_Cate_Request;
import com.enterprise.backend.DTO.ReactionRequest;
import com.enterprise.backend.model.*;
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


import java.security.PublicKey;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/idea")
@CrossOrigin(origins = "http://localhost:3000")
public class IdeaController {

    Logger logger = LoggerFactory.getLogger(IdeaController.class);

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


/*    @PostMapping("/cate_idea")
    public String insertidea_catev2(@RequestParam long cate_id, @RequestParam long idea_id) {
        ideaService.insertv2(cate_id, idea_id);
        return "OK ADDED";
    }*/

    @PostMapping("/cate_idea")
    public String add(@RequestBody Idea_Cate_Request ideaCateRequest) {

        logger.info("cate_id logger ==>" + ideaCateRequest.getCategories());

        for (long cateid : ideaCateRequest.getCategories()) {
            logger.info("cate_id logger ==>" + cateid);
            try {
                ideaService.insertv2(cateid, ideaCateRequest.getIdea_id());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        logger.info("cate_id logger 123 ==>");

        return "OK ADDED";
    }


    @PostMapping("/comment")
    public Comment addcomment(@RequestBody CommentRequest commentRequest) {

        return ideaService.insertcomment(commentRequest);
    }

    @PostMapping("/reaction")
    public  Reaction addreaction (@RequestBody ReactionRequest reactionRequest){

        return ideaService.insertreaction(reactionRequest);
    }


}
