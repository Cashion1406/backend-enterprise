package com.enterprise.backend.controller;


import com.enterprise.backend.DTO.ClientDepartmentRequest;

import com.enterprise.backend.DTO.Client_Topic_Request;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.response.FollowTopic;
import com.enterprise.backend.service.ClientService;
import com.enterprise.backend.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    @Autowired
    private ClientService clientService;


    //get list of all user
    @JsonView(View.SumwithDepartment.class)
    @GetMapping()
    public List<Client> getallClient() {

        return clientService.getallClient();
    }

    //create user info
    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:3000")
    public Client addClient(@RequestBody ClientDepartmentRequest client) {


        return clientService.saveClient(client);
    }
    @JsonView(View.SumwithDepartment.class)
    @GetMapping("/{id}")
    public Optional<Client> getClientByid(@PathVariable String id) {

        return clientService.getClientByid(id);
    }

    @GetMapping("/searchbyname")
    public List<Client> getClientbyName(@RequestParam String name) {
        return clientService.getClientByname(name);

    }

    @DeleteMapping("/delete")
    public DeleteResponse delete(@PathVariable String id) {

        return clientService.delete(id);
    }

    @PutMapping("/update")
    public Client update(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @PostMapping("/deleteall")
    public String deleteall() {

        return clientService.deleteAllClient();
    }

    @PostMapping("/topic")
    public String insertfollowtopic(@RequestBody Client_Topic_Request clientTopicRequest) {
        clientService.followtopic(clientTopicRequest);
        return "Added topics to Client " + clientService.getClientname(clientTopicRequest.getClient_id());

    }

    @GetMapping("/topic/{id}")
    public List<FollowTopic> followTopic (@PathVariable String id ){


        return clientService.followTopic(id);

    }

    @GetMapping("/idea/{id}")
    public List<Idea> getideawithclient(@PathVariable String id) {

        return clientService.getideabyclientid(id);
    }

}