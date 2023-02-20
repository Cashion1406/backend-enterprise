package com.enterprise.backend.service;

import com.enterprise.backend.model.Category;
import com.enterprise.backend.repo.CateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateService {

    @Autowired
    private CateRepo cateRepo;

    public Category savecate(Category category) {

        return cateRepo.save(category);
    }

    public Category updatecate(Category category) {

        Category existcate = cateRepo.findById(category.getId()).get();
        existcate.setName(category.getName());

        return cateRepo.save(category);
    }

    public List<Category> getallcate() {
        return cateRepo.findAll();
    }

    public String deletecate(long id) {
        cateRepo.deleteById(id);
        return "Category deleted";
    }


}
