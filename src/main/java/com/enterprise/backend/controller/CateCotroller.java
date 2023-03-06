package com.enterprise.backend.controller;

import com.enterprise.backend.model.Category;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.service.CateService;
import com.enterprise.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cate")
@CrossOrigin(origins = "http://localhost:3000")
public class CateCotroller {
    @Autowired
    private CateService cateService;


    @GetMapping()
    public List<Category> getallClient() {

        return cateService.getallcate();
    }

    @PostMapping("/create")
        public Category createCate(@RequestBody Category category ) {
        return cateService.savecate(category);
    }


    @PutMapping("/update")
    public Category updateCate(@RequestBody Category category){
        return cateService.updatecate(category);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deletecate(@PathVariable long id){

        return cateService.deletecate(id);
    }
}
