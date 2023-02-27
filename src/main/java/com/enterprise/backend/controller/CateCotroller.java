package com.enterprise.backend.controller;

import com.enterprise.backend.model.Category;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.service.CateService;
import com.enterprise.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cate")
public class CateCotroller {
    @Autowired
    private CateService cateService;


    @GetMapping()
    public List<Category> getallClient() {

        return cateService.getallcate();
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
        public Category addClient(@RequestBody Category category ) {
        return cateService.savecate(category);
    }


}
