package com.enterprise.backend.service;

import com.enterprise.backend.DTO.Department.DepartmentRequest;
import com.enterprise.backend.model.Department;
import com.enterprise.backend.repo.DepartmentRepo;
import com.enterprise.backend.response.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;


    public Department createDepartment(Department department){

        if (department.getIsDeleted()==null){

            department.setIsDeleted(false);
        }
        return departmentRepo.save(department);

    }

    public List<Department> getAllDepartment (){

        //use departmentRepo.findAll() to get all current department
        return departmentRepo.findByisDeletedFalse();
    }

    public DeleteResponse deletedDepartment (long id){


        departmentRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return new DeleteResponse("Deleted "+ departmentRepo.departmentnamebyid(id),timestamp, true);

    }


    public Department updateDepartment(DepartmentRequest departmentRequest) {

        Department existDepartment = departmentRepo.findById(departmentRequest.getId()).get();

        existDepartment.setDepartment_info(departmentRequest.getDepartment_info());
        existDepartment.setName(departmentRequest.getName());

        existDepartment.setDepartment_info(departmentRequest.getDepartment_info());
        return departmentRepo.save(existDepartment);
    }
}
