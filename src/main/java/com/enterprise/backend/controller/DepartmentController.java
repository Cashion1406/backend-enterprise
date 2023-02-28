package com.enterprise.backend.controller;


import com.enterprise.backend.model.Department;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:3000")

public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public List<Department> gelldepament(){
        return  departmentService.getall();
    }

    @PostMapping("/create")
    public  Department createdepartment(@RequestBody Department department){

        return departmentService.create(department);
    }

    @DeleteMapping("/{id}")
    public DeleteResponse deletedepartment(long id){

        return departmentService.deletedepartment(id);
    }





}
