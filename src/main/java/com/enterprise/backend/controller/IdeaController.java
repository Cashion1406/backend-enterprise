package com.enterprise.backend.controller;

import com.enterprise.backend.DTO.CommentRequest;
import com.enterprise.backend.DTO.Idea.IdeaRequest;
import com.enterprise.backend.DTO.Idea.Idea_Cate_Request;
import com.enterprise.backend.DTO.Reaction.ReactionUpdateRequest;
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
    public List<Idea> getallidea() {
        return ideaService.getallidea();
    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public Idea createtopic(@RequestBody IdeaRequest idea) {
        return ideaService.createidea(idea);
    }

    @GetMapping("/{id}")
    public Optional<Idea> getallidea(@PathVariable long id) {

        return ideaService.get(id);
    }


    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteidea(@PathVariable Long id){

        return ideaService.deleteidea(id);
    }

/*    @GetMapping("/gettotalview/{id}")
    public Integer getallidea(@PathVariable Long id) {

        return ideaService.gettotalview(id);
    }

    @GetMapping("/getupvote")
    public List<Idea> upvote() {

        return ideaService.getupvote();
    }*/


/*    @PostMapping("/cate_idea")
    public String insertidea_catev2(@RequestParam long cate_id, @RequestParam long idea_id) {
        ideaService.insertv2(cate_id, idea_id);
        return "OK ADDED";
    }*/

    @PostMapping("/cate_idea")
    public String add(@RequestBody Idea_Cate_Request ideaCateRequest) {


        ideaService.insertIdeaCate(ideaCateRequest);

        return "Added Category to Idea: " + ideaService.getideaname(ideaCateRequest.getIdea_id());
    }
    @PutMapping("/update")
    public Idea updateidea(@RequestBody Idea idea){

        return ideaService.updateidea(idea);
    }


    @PostMapping("/comment")
    public Comment addcomment(@RequestBody CommentRequest commentRequest) {

        return ideaService.insertcomment(commentRequest);
    }

    @PostMapping("/reaction")
    public  Reaction addreaction (@RequestBody ReactionRequest reactionRequest){

        return ideaService.insertreaction(reactionRequest);
    }

    @PutMapping("/reaction/update")
    public Reaction updatereaction(@RequestBody ReactionUpdateRequest reactionRequest){

        return ideaService.updatereaction(reactionRequest);
    }


}
