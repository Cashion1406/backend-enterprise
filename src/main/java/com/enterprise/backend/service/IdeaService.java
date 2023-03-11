package com.enterprise.backend.service;

import com.enterprise.backend.DTO.Client.Client_Department_QA_DE;
import com.enterprise.backend.DTO.CommentRequest;
import com.enterprise.backend.DTO.EmailMessage;
import com.enterprise.backend.DTO.Idea.IdeaRequest;

import com.enterprise.backend.DTO.Idea.Idea_Cate_Request;
import com.enterprise.backend.DTO.ReactionRequest;
import com.enterprise.backend.model.*;
import com.enterprise.backend.repo.*;
import com.enterprise.backend.response.DeleteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class IdeaService {
    Logger logger = LoggerFactory.getLogger(IdeaService.class);
    @Autowired
    private MailService mailService;

    @Autowired
    private IdeaRepo ideaRepo;
    @Autowired
    private CateRepo cateRepo;
    @Autowired
    private ReactionRepo reactionRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private NotificationRepo notificationRepo;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TopicService topicServce;

    public List<Idea> getAllIdea() {

        return ideaRepo.findAll();
    }

    public Idea createIdea(IdeaRequest idea) {

        Client client = clientService.getClientByid(idea.getClient_id()).get();
        Topic topic = topicServce.getTopicById(idea.getTopic_id()).get();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        Idea newIdea = new Idea();
        newIdea.setBody(idea.getBody());
        newIdea.setName(idea.getName());
        newIdea.setDate(timeStamp);
        newIdea.setAttached_path(idea.getAttached_path());
        newIdea.setModify_date(timeStamp);
        newIdea.setClient(client);
        newIdea.setTopic(topic);
        newIdea.setIsAnonymous(idea.getIsAnonymous());
        try {
            //sendmail( String toEmailAddress, String Subject , String body )
            Client client_QA = clientService.getClientQA(client.getDepartment().getId());
            mailService.sendMail(client_QA.getEmail(), topic.getName(), client.getFirstname() + " " + client.getLastname() + " of " + client.getDepartment().getName() + " has created a new idea with title " + newIdea.getName() + " at " + " " + timeStamp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ideaRepo.save(newIdea);
    }


    //Update Idea
    public Idea updateIdea(IdeaRequest ideaUpdateRequest) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        Idea existIdea = ideaRepo.findById(ideaUpdateRequest.getId()).get();
        existIdea.setBody(ideaUpdateRequest.getBody());
        existIdea.setName(ideaUpdateRequest.getName());
        existIdea.setModify_date(timeStamp);
        existIdea.setAttached_path(ideaUpdateRequest.getAttached_path());
        existIdea.setIsAnonymous(ideaUpdateRequest.getIsAnonymous());

        return ideaRepo.save(existIdea);
    }


    //Get all available Idea
    public Optional<Idea> getIdeaById(long id) {


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
    public Comment insertComment(CommentRequest commentRequest) {

        Optional<Client> client = clientService.getClientByid(commentRequest.getClient_id());
        Optional<Idea> idea = ideaRepo.findById(commentRequest.getIdea_id());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        Comment newComment = new Comment();
        newComment.setClient(client.get());
        newComment.setIdea(idea.get());
        newComment.setComment(commentRequest.getComment());
        newComment.setModify_date(timeStamp);
        newComment.setIsAnonymous(commentRequest.getIsAnonymous());
        Notification notification = new Notification();
        notification.setIsDelete(false);
        notification.setStatus(false);
        notification.setCreatedAt(timeStamp);
        notification.setClient_id(commentRequest.getClient_id());
        notification.setContent(client.get().getFirstname() + " has commented on your idea :" + idea.get().getName());
        notificationRepo.save(notification);
        return commentRepo.save(newComment);
    }


    //Add reaction to idea
    public Reaction insertReaction(ReactionRequest reactionRequest) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        Optional<Client> client = clientService.getClientByid(reactionRequest.getClient_id());
        Optional<Idea> idea = ideaRepo.findById(reactionRequest.getIdea_id());
        Reaction newReaction = new Reaction();
        newReaction.setIdea(idea.get());
        newReaction.setClient(client.get());
        newReaction.setReaction(reactionRequest.getReaction());
        String status = "";
        if (reactionRequest.getReaction()) {

            status = "up vote";
        } else {
            status = "down vote";
        }

        Notification notification = new Notification();
        notification.setIsDelete(false);
        notification.setStatus(false);
        notification.setCreatedAt(timeStamp);
        notification.setClient_id(reactionRequest.getClient_id());
        notification.setContent(client.get().getFirstname() + " has " + status + " on your idea : " + idea.get().getName());
        notificationRepo.save(notification);
        return reactionRepo.save(newReaction);

    }

    //Update reaction
    public Reaction updateReaction(ReactionRequest reactionRequest) {

        Reaction existReaction = reactionRepo.getReaction(reactionRequest.getClient_id(), reactionRequest.getIdea_id());
        existReaction.setReaction(reactionRequest.getReaction());
        return reactionRepo.save(existReaction);

    }

    public DeleteResponse deleteIdea(Long id) {
        String name = ideaRepo.getideaname(id);
        //String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ideaRepo.deleteById(id);
        return new DeleteResponse("Delete idea " + name, timestamp, true);
    }


    //get idea name with ID
    public String getIdeaName(Long id) {

        return ideaRepo.getideaname(id);
    }


}
