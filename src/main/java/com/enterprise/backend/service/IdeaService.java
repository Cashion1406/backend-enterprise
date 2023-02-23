package com.enterprise.backend.service;

import com.enterprise.backend.model.Category;
import com.enterprise.backend.model.Idea;
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

    public List<Idea> getallidea() {

        return ideaRepo.findAll();
    }

    public Idea createidea(Idea idea) {



        return ideaRepo.save(idea);
    }




}
