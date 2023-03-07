package com.enterprise.backend.controller;


import com.enterprise.backend.DTO.Client.ClientDepartmentRequest;

import com.enterprise.backend.DTO.Client.ClientUpdateRequest;
import com.enterprise.backend.DTO.Client.Client_Topic_Request;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.response.ClientReaction;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.response.FollowTopic;
import com.enterprise.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping()
    public List<Client> getAllClient() {

        return clientService.getallClient();
    }

    //create user info
    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:3000")
    public Client addClient(@RequestBody ClientDepartmentRequest client) {


        return clientService.saveClient(client);
    }

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable String id) {

        return clientService.getClientByid(id);
    }

    @GetMapping("/searchbyname")
    public List<Client> getClientbyName(@RequestParam String name) {
        return clientService.getClientByname(name);

    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteClient(@PathVariable String id) {

        return clientService.delete(id);
    }

    @PutMapping("/update")
    public Client updateClient(@RequestBody ClientUpdateRequest client) {
        return clientService.updateClient(client);
    }

    @PostMapping("/deleteall")
    public String deleteAllClient() {

        return clientService.deleteAllClient();
    }

    @PostMapping("/topic")
    public String insertFollowTopic(@RequestBody Client_Topic_Request clientTopicRequest) {
        clientService.followtopic(clientTopicRequest);
        return "Added topics to Client " + clientService.getClientname(clientTopicRequest.getClient_id());

    }

    @GetMapping("/topic/{id}")
    public List<FollowTopic> getFollowTopic(@PathVariable String id) {


        return clientService.followTopic(id);

    }

    @GetMapping("/reaction/{id}")
    public List<ClientReaction> getReactionWithClient(@PathVariable String id) {

        return clientService.getclientreaction(id);
    }


    @GetMapping("/idea/{id}")
    public List<Idea> getIdeaWithClient(@PathVariable String id) {

        return clientService.getideabyclientid(id);
    }


}
