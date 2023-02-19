package com.enterprise.backend.service;

import com.enterprise.backend.DTO.FileUploadUtil;
import com.enterprise.backend.model.FileUpload;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.model.Topic;
import com.enterprise.backend.repo.IdeaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public ResponseEntity<FileUpload> upload(MultipartFile multipartFile) throws IOException {

        String filename = multipartFile.getOriginalFilename();
        FileUploadUtil.savefile(filename, multipartFile);

        return ResponseEntity.ok(new FileUpload(filename,null,multipartFile.getSize()));
    }

}
