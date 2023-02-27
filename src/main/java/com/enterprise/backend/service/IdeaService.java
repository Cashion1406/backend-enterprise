package com.enterprise.backend.service;

import com.enterprise.backend.DTO.IdeaRequest;
import com.enterprise.backend.model.*;
import com.enterprise.backend.repo.CateRepo;
import com.enterprise.backend.repo.IdeaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Idea> get(String id){

        return ideaRepo.getideabyid(id);
    }

    public int gettotalview(Long id){

        return ideaRepo.gettotalview(id);
    }


    public List<Idea> getupvote(){
        return ideaRepo.getupvote();
    }

    public String insertv2(long cate_id, long idea_id){

        return ideaRepo.insertideacatev2(cate_id,idea_id);
    }


}
