package com.enterprise.backend.service;

import com.enterprise.backend.DTO.CommentRequest;
import com.enterprise.backend.DTO.Idea.IdeaRequest;
import com.enterprise.backend.DTO.Idea.Idea_Cate_Request;
import com.enterprise.backend.DTO.Reaction.ReactionUpdateRequest;
import com.enterprise.backend.DTO.ReactionRequest;
import com.enterprise.backend.model.*;
import com.enterprise.backend.repo.CateRepo;
import com.enterprise.backend.repo.CommentRepo;
import com.enterprise.backend.repo.IdeaRepo;
import com.enterprise.backend.repo.ReactionRepo;
import com.enterprise.backend.response.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

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


    //Get all available Idea
    public Optional<Idea> get(long id) {


        return ideaRepo.findById(id);
    }

    public int gettotalview(Long id) {

        return ideaRepo.gettotalview(id);
    }


    //Add category to idea
    public void insertIdeaCate(Idea_Cate_Request ideaCateRequest) {

        for (long cateid : ideaCateRequest.getCategories()) {

            try {
                ideaRepo.insertideacatev2(cateid, ideaCateRequest.getIdea_id());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //Add comment to Idea
    public Comment insertcomment(CommentRequest commentRequest) {

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


    //Add reaction to idea
    public Reaction insertreaction(ReactionRequest reactionRequest) {

        Optional<Client> client = clientService.getClientByid(reactionRequest.getClient_id());
        Optional<Idea> idea = ideaRepo.findById(reactionRequest.getIdea_id());
        Reaction newReaction = new Reaction();
        newReaction.setIdea(idea.get());
        newReaction.setClient(client.get());
        newReaction.setReaction(reactionRequest.getReaction());
        return reactionRepo.save(newReaction);

    }

    //Update reaction
    public Reaction updatereaction(ReactionUpdateRequest reactionUpdateRequest){

        Reaction existReaction = reactionRepo.findById(reactionUpdateRequest.getReaction_id()).get();
        existReaction.setReaction(reactionUpdateRequest.getReaction());
        return reactionRepo.save(existReaction);

    }

    public DeleteResponse deleteidea(Long id) {
        String name = ideaRepo.getideaname(id);
        //String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ideaRepo.deleteById(id);
        return new DeleteResponse("Delete idea " + name, timestamp, true);
    }


    //get idea name with ID
    public String getideaname(Long id) {

        return ideaRepo.getideaname(id);
    }


    //Update Idea
    public Idea updateidea(Idea idea) {

        Idea existIdea = ideaRepo.findById(idea.getId()).get();
        existIdea.setBody(idea.getBody());
        existIdea.setName(idea.getName());
        existIdea.setDate(idea.getDate());
        existIdea.setAttached_path(idea.getAttached_path());
        return ideaRepo.save(existIdea);
    }
}
