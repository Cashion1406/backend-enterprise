package com.enterprise.backend.controller;

import com.enterprise.backend.DTO.CommentRequest;
import com.enterprise.backend.DTO.Idea.IdeaRequest;
import com.enterprise.backend.DTO.Idea.Idea_Cate_Request;

import com.enterprise.backend.DTO.Idea.IdeasPerCate;
import com.enterprise.backend.DTO.Idea.IdeasPerDepartment;
import com.enterprise.backend.DTO.ReactionRequest;
import com.enterprise.backend.model.*;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/idea")
@CrossOrigin(origins = "http://localhost:3000")
public class IdeaController {


    @Autowired
    private IdeaService ideaService;


    @GetMapping()
    public List<Idea> getAllIdea() {
        return ideaService.getAllIdea();
    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public Idea createIdea(@RequestBody IdeaRequest idea) {
        return ideaService.createIdea(idea);
    }

    @GetMapping("/{id}")
    public Optional<Idea> getIdeaById(@PathVariable long id) {

        return ideaService.getIdeaById(id);
    }


    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteIdea(@PathVariable Long id) {

        return ideaService.softDeleteIdea(id);
    }


    @PostMapping("/cate_idea")
    public String addCateToIdea(@RequestBody Idea_Cate_Request ideaCateRequest) {


        ideaService.insertIdeaCate(ideaCateRequest);

        return "Added Category to Idea: " + ideaService.getIdeaName(ideaCateRequest.getIdea_id());
    }

    @PutMapping("/update")
    public Idea updateIdea(@RequestBody IdeaRequest idea) {

        return ideaService.updateIdea(idea);
    }


    @PostMapping("/comment")
    public Comment addComment(@RequestBody CommentRequest commentRequest) {

        return ideaService.insertComment(commentRequest);
    }

    @PutMapping("/comment/update")
    public Comment updateComment(@RequestBody CommentRequest commentRequest) {
        return ideaService.updateComment(commentRequest);
    }

    @DeleteMapping("/comment/delete/{id}")
    public DeleteResponse deleteComment(@PathVariable Long id) {
        return ideaService.deleteComment(id);
    }

    @PostMapping("/reaction")
    public Reaction addReaction(@RequestBody ReactionRequest reactionRequest) {

        return ideaService.insertReaction(reactionRequest);
    }

    @PutMapping("/reaction/update")
    public Reaction updateReaction(@RequestBody ReactionRequest reactionRequest) {

        return ideaService.updateReaction(reactionRequest);
    }

    @GetMapping("/department")
    public List<IdeasPerDepartment> ideasPerDepartment() {
        return ideaService.ideasPerDepartment();
    }


    @GetMapping("/top7ideas")
    public List<IdeasPerCate> ideasPerCates() {
        return ideaService.ideasPerCate();
    }

    @GetMapping("/top5views")
    public List<Idea> top5views() {

        return ideaService.top5views();
    }

}
