package com.enterprise.backend.controller;


import com.enterprise.backend.DTO.Client.ClientDepartmentRequest;

import com.enterprise.backend.DTO.Client.ClientNotification;
import com.enterprise.backend.DTO.Client.ClientUpdateRequest;
import com.enterprise.backend.DTO.Client.Client_Topic_Request;
import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.response.ClientReaction;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.response.FollowTopic;
import com.enterprise.backend.service.ClientService;
import com.enterprise.backend.service.ExportDataService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ExportDataService exportDataService;


    //get list of all user
    @GetMapping()
    public List<Client> getAllClient() {

        return clientService.getAllClient();
    }

    //create user info
    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:3000")
    public Client addClient(@RequestBody ClientDepartmentRequest client) {


        return clientService.saveClient(client);
    }

    //get user detail with id
    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable String id) {

        return clientService.getClientByid(id);
    }

    //search list of user with name
    @GetMapping("/searchbyname")
    public List<Client> getClientbyName(@RequestParam String name) {
        return clientService.getClientByName(name);

    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteClient(@PathVariable String id) {

        return clientService.deleteClient(id);
    }

    //update user detail
    @PutMapping("/update")
    public Client updateClient(@RequestBody ClientUpdateRequest client) {
        return clientService.updateClient(client);
    }

    @PostMapping("/deleteall")
    public String deleteAllClient() {

        return clientService.deleteAllClient();
    }

    //set user follow topic
    @PostMapping("/topic")
    public String insertFollowTopic(@RequestBody Client_Topic_Request clientTopicRequest) {
        clientService.followTopic(clientTopicRequest);
        return "Added topics to Client " + clientService.getClientLastName(clientTopicRequest.getClient_id());
    }


    //get user's current follwed topic
    @GetMapping("/topic/{id}")
    public List<FollowTopic> getFollowTopic(@PathVariable String id) {


        return clientService.followTopic(id);

    }

    //user unfollow topic
    @DeleteMapping("/topic/delete")
    public String removeFollowTopic(@RequestBody Client_Topic_Request clientTopicRequest) {
        return clientService.removeTopic(clientTopicRequest.getClient_id(), clientTopicRequest.getTopic_id());
    }

    @GetMapping("/reaction/{id}")
    public List<ClientReaction> getReactionWithClient(@PathVariable String id) {

        return clientService.getClientReaction(id);
    }


    @GetMapping("/idea/{id}")
    public List<Idea> getIdeaWithClient(@PathVariable String id) {

        return clientService.getIdeaByClientId(id);
    }

    @GetMapping("/notification/{id}")
    public List<ClientNotification> getUserNotification(@PathVariable String id) {

        return clientService.clientNotifications(id);
    }

    //testing
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        exportDataService.downloadtoCSV(response);
    }
}
