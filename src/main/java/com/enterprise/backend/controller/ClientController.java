package com.enterprise.backend.controller;


import com.enterprise.backend.DTO.ClientDepartmentRequest;

import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.ERole;
import com.enterprise.backend.response.DeleteResponse;
import com.enterprise.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping()
    public List<Client> getallClient() {

        return clientService.getallClient();
    }


    //create user info
    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:3000")
    @Transactional
    public Client addClient(@RequestBody ClientDepartmentRequest client) {


        return clientService.saveClient(client);
    }

    @GetMapping("/{id}")
    public Optional<Client> getClientByid(@PathVariable String id) {

        return clientService.getClientByid(id);
    }

    @GetMapping("/searchbyname")
    public List<Client> getClientbyName(@RequestParam String name) {
        return clientService.getClientByname(name);

    }

    @DeleteMapping("/delete")
    public DeleteResponse delete(@RequestParam String id) {

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

}