package com.enterprise.backend.service;

import com.enterprise.backend.DTO.Topic.TopicRequest;
import com.enterprise.backend.DTO.Topic.TopicWithMostFollowers;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.repo.TopicRepo;
import com.enterprise.backend.response.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepo topicRepo;


    public List<Topic> getAllTopic() {

        //use topicRepo.findAll() to get all current topic

        return topicRepo.findByisDeletedFalse();
    }

    public Topic createTopic(TopicRequest topicRequest) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

        Topic newtopic = new Topic();
        newtopic.setName(topicRequest.getName());
        newtopic.setImageURL(topicRequest.getImageURL());
        newtopic.setFinal_closure_date(topicRequest.getFinal_closure_date());
        newtopic.setTopic_closure_date(topicRequest.getTopic_closure_date());
        newtopic.setModifyDate(timeStamp);
        newtopic.setImageURL(topicRequest.getImageURL());
        newtopic.setIsDeleted(false);
        newtopic.setDescription(topicRequest.getDescription());

        return topicRepo.save(newtopic);
    }

    public Optional<Topic> getTopicById(Long id) {
        return topicRepo.findById(id);
    }


    public Topic updateTopic(TopicRequest topicRequest) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());

        Topic existtopic = topicRepo.findById(topicRequest.getId()).get();
        existtopic.setName(topicRequest.getName());
        existtopic.setTopic_closure_date(topicRequest.getTopic_closure_date());
        existtopic.setFinal_closure_date(topicRequest.getFinal_closure_date());
        existtopic.setModifyDate(timeStamp);
        existtopic.setDescription(topicRequest.getDescription());
        existtopic.setIsDeleted(topicRequest.getIsDeleted());
        
        return topicRepo.save(existtopic);
    }

    public DeleteResponse deleteTopic(Long id) {
        String topicname = topicRepo.getTopicname(id);
        topicRepo.deleteById(id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return new DeleteResponse("Deleted topic " + topicname, timestamp, true);
    }

    public String softDeleteTopic(Long id) {
        String topicname = topicRepo.getTopicname(id);

        topicRepo.softdeletetopic(id);
        return "Temporary deleted " + topicname;
    }

    public List<TopicWithMostFollowers>  top7follower() {

        return topicRepo.top7followers();
    }
}
