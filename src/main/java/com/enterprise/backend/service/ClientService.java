package com.enterprise.backend.service;



import com.enterprise.backend.DTO.ClientDepartmentRequest;

import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Department;
import com.enterprise.backend.model.ERole;
import com.enterprise.backend.repo.ClientRepo;
import com.enterprise.backend.repo.DepartmentRepo;
import com.enterprise.backend.response.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private DepartmentRepo departmentRepo;


    //Create User
    public Client saveClient(ClientDepartmentRequest clientDepartmentRequest) {

        Optional<Department> department = departmentRepo.findById(clientDepartmentRequest.getDepartment_id());

        Client newClient = new Client();
        newClient.setFirstname(clientDepartmentRequest.getFirstname());
        newClient.setLastname(clientDepartmentRequest.getLastname());
        newClient.setClient_info(clientDepartmentRequest.getClient_info());
        newClient.setAge(clientDepartmentRequest.getAge());
        newClient.setDepartment(department.get());
        if (clientDepartmentRequest.getIsDeleted() ==null ){
            newClient.setIsDeleted(false);
        }
        if (clientDepartmentRequest.getRole() ==null){

            newClient.setRole(ERole.ROLE_USER);
        }


        return clientRepo.save(newClient);
    }


    //Get list of Users
    public List<Client> getallClient() {

        return clientRepo.findAll();
    }

    public Optional<Client> getClientByid(String id) {

        return clientRepo.findById(id);
    }


    //Get User by Id
    public DeleteResponse delete(String id) {
        clientRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new DeleteResponse("Deleted user " + id, timestamp, true);
    }


    //Update User
    public Client updateClient(Client client) {
        Client existClient = clientRepo.findById(String.valueOf(client.getId())).get();
        existClient.setClient_info(client.getClient_info());
        return clientRepo.save(existClient);
    }

/*    public Optional<Client> getClientByname(String firstname) {

        return clientRepo.findBynameContaining(firstname);
    }*/

    //Delete all User in db
    public String deleteAllClient() {
        clientRepo.deleteAll();
        return "SUCCESSFULLY DELETE ALL USER";
    }

    //Get list User by last name
    public List<Client> getClientByname(String name) {
        if (name.isEmpty()){

            return clientRepo.findAll();
        }
        return clientRepo.findBylastnameContaining(name);
    }

}