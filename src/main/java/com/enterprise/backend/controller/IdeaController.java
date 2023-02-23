package com.enterprise.backend.controller;

import com.enterprise.backend.model.Idea;
import com.enterprise.backend.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/idea")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @GetMapping()
    public List<Idea> getallidea() {

        return ideaService.getallidea();
    }

    @PostMapping("/create")
    public Idea createtopic(@RequestBody Idea idea) {


        return ideaService.createidea(idea);
    }

   }
