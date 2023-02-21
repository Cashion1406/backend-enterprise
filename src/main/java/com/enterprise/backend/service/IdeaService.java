package com.enterprise.backend.service;

import com.enterprise.backend.model.Idea;
import com.enterprise.backend.repo.IdeaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaService {
    @Autowired
    private IdeaRepo ideaRepo;

    public List<Idea> getallidea() {

        return ideaRepo.findAll();
    }

    public Idea createidea(Idea idea) {


        return ideaRepo.save(idea);
    }


}
