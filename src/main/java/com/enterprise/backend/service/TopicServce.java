package com.enterprise.backend.service;

import com.enterprise.backend.model.Topic;
import com.enterprise.backend.repo.TopicRepo;
import com.enterprise.backend.response.DeleteResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServce {

    @Autowired
    private TopicRepo topicRepo;


    public List<Topic> getalltopic() {

        //use topicRepo.findAll() to get all current topic

        return topicRepo.findByisDeletedFalse();
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

    public DeleteResponse deleteTopic (Long id){
        String topicname = topicRepo.getTopicname(id);
        topicRepo.deleteById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return new DeleteResponse("Delete topic " + topicname, timestamp, true);
    }

    public String softdelete(Long id ){
        String topicname = topicRepo.getTopicname(id);

        topicRepo.softdeletetopic(id);
        return "Temporary deleted " + topicname;
    }
}
