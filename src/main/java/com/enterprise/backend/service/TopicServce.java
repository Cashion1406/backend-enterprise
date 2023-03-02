package com.enterprise.backend.service;

import com.enterprise.backend.model.Topic;
import com.enterprise.backend.repo.TopicRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServce {

    @Autowired
    private TopicRepo topicRepo;


    public List<Topic> getalltopic() {

        return topicRepo.findAll();
    }

    public Topic createtopic(Topic topic) {

        return topicRepo.save(topic);
    }

    public Optional<Topic> gettopicbyid(Long id){
        return topicRepo.findById(id);
    }


    public Topic updateTopic (Topic topic){

        Topic existtopic = topicRepo.findById(topic.getId()).get();
        existtopic.setName(topic.getName());
        existtopic.setIdea_closure_date(topic.getIdea_closure_date());
        existtopic.setFinal_closure_date(topic.getFinal_closure_date());
        existtopic.setModifyDate(topic.getModifyDate());
        existtopic.setIsDeleted(topic.getIsDeleted());

        return topicRepo.save(existtopic);
    }

    public String deleteTopic (Long id){
        String topicname = topicRepo.getTopicname(id);
        topicRepo.deleteById(id);

        return "Successfully deleted "+topicname  ;
    }

    public String softdelete(Long id ){
        String topicname = topicRepo.getTopicname(id);

        topicRepo.softdeletetopic(id);
        return "Temporary deleted" + topicname;
    }
}
