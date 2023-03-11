package com.enterprise.backend.service;


import com.enterprise.backend.DTO.Client.ClientDepartmentRequest;

import com.enterprise.backend.DTO.Client.ClientUpdateRequest;
import com.enterprise.backend.DTO.Client.Client_Department_QA_DE;
import com.enterprise.backend.DTO.Client.Client_Topic_Request;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Department;
import com.enterprise.backend.model.ERole;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.repo.ClientRepo;
import com.enterprise.backend.repo.DepartmentRepo;
import com.enterprise.backend.repo.IdeaRepo;
import com.enterprise.backend.repo.TopicRepo;
import com.enterprise.backend.response.ClientReaction;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.response.FollowTopic;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private IdeaRepo ideaRepo;

    @Autowired
    private TopicRepo topicRepo;

    //Create User
    public Client saveClient(ClientDepartmentRequest clientDepartmentRequest) {

        Optional<Department> department = departmentRepo.findById(clientDepartmentRequest.getDepartment_id());
        Client newClient = new Client();
        newClient.setId(clientDepartmentRequest.getId());
        newClient.setFirstname(clientDepartmentRequest.getFirstname());
        newClient.setPronoun(clientDepartmentRequest.getPronoun());
        newClient.setLastname(clientDepartmentRequest.getLastname());
        newClient.setEmail(clientDepartmentRequest.getEmail());
        newClient.setClient_info(clientDepartmentRequest.getClient_info());
        newClient.setAge(clientDepartmentRequest.getAge());
        newClient.setDepartment(department.get());
        if (clientDepartmentRequest.getIsDeleted() == null) {
            newClient.setIsDeleted(false);
        }
        if (clientDepartmentRequest.getRole() == null) {

            newClient.setRole(ERole.ROLE_USER);
        }

        return clientRepo.save(newClient);
    }


    //Get list of Users
    public List<Client> getAllClient() {

        return clientRepo.findAll();
    }

    public Optional<Client> getClientByid(String id) {

        return clientRepo.findById(id);
    }

    public String getClientLastName(String id) {

        return clientRepo.getClientLastName(id);
    }

    public String getClientFirstName(String id){

        return clientRepo.getClientFirstName(id);
    }


    //Delete User by Id
    public DeleteResponse deleteClient(String id) {
        clientRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new DeleteResponse("Deleted user " + id, timestamp, true);
    }


    //Update User
    public Client updateClient(ClientUpdateRequest client) {
        Client existClient = clientRepo.findById(String.valueOf(client.getId())).get();
        existClient.setClient_info(client.getClient_info());
        existClient.setAge(client.getAge());
        existClient.setEmail(client.getEmail());
        existClient.setFirstname(client.getFirstname());
        existClient.setLastname(client.getLastname());
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
    public List<Client> getClientByName(String name) {
        if (name.isEmpty()) {

            return clientRepo.findAll();
        }
        return clientRepo.findBylastnameContaining(name);
    }

    public void followTopic(Client_Topic_Request clientTopicRequest) {


        try {
            clientRepo.insertfollowtopic(clientTopicRequest.getTopic_id(), clientTopicRequest.getClient_id());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String removeTopic(String clientId, Long topicId) {
        try {
            clientRepo.removeFollowTopic(topicId, clientId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Unfollowed Topic : " +topicRepo.getTopicname(topicId);
    }

    public List<FollowTopic> followTopic(String client_id) {

        return clientRepo.findfollowtopic(client_id);
    }


    public List<Idea> getIdeaByClientId(String id) {

        return ideaRepo.getideabyclientid(id);
    }

    public List<ClientReaction> getClientReaction(String id) {
        return clientRepo.findClientReaction(id);
    }

    public  Client getClientQA (Long id){

        return clientRepo.getClientQA(id);
    }
}