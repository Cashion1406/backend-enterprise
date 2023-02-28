package com.enterprise.backend.service;

import com.enterprise.backend.DTO.CommentRequest;
import com.enterprise.backend.DTO.IdeaRequest;
import com.enterprise.backend.DTO.Idea_Cate_Request;
import com.enterprise.backend.DTO.ReactionRequest;
import com.enterprise.backend.model.*;
import com.enterprise.backend.repo.CateRepo;
import com.enterprise.backend.repo.CommentRepo;
import com.enterprise.backend.repo.IdeaRepo;
import com.enterprise.backend.repo.ReactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IdeaService {
    @Autowired
    private IdeaRepo ideaRepo;

    @Autowired
    private CateRepo cateRepo;
    @Autowired
    private ReactionRepo reactionRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ClientService clientService;
    @Autowired
    private TopicServce topicServce;

    public List<Idea> getallidea() {

        return ideaRepo.findAll();
    }

    public Idea createidea(IdeaRequest idea) {

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
        return ideaRepo.save(newIdea);
    }

    public List<Idea> get(String id) {

        return ideaRepo.getideabyid(id);
    }

    public int gettotalview(Long id) {

        return ideaRepo.gettotalview(id);
    }

    public List<Idea> getupvote() {
        return ideaRepo.getupvote();
    }

    public void insertv2(long cate_id, long idea_id) {

         ideaRepo.insertideacatev2(cate_id, idea_id);
    }

    public Comment insertcomment(CommentRequest commentRequest){

        Optional<Client> client = clientService.getClientByid(commentRequest.getClient_id());
        Optional<Idea> idea = ideaRepo.findById(commentRequest.getIdea_id());


        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        Comment newComment = new Comment();
        newComment.setClient(client.get());
        newComment.setIdea(idea.get());
        newComment.setComment(commentRequest.getComment());
        newComment.setModify_date(timeStamp);

        return commentRepo.save(newComment);
    }

    public Reaction insertreaction (ReactionRequest reactionRequest){

        Optional<Client> client = clientService.getClientByid(reactionRequest.getClient_id());

        Optional<Idea> idea = ideaRepo.findById(reactionRequest.getIdea_id());

        Reaction newReaction = new Reaction();
        newReaction.setIdea(idea.get());
        newReaction.setClient(client.get());
        newReaction.setReaction(reactionRequest.getReaction());

        return reactionRepo.save(newReaction);

    }



}
