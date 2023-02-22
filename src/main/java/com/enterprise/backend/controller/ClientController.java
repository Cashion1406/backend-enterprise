package com.enterprise.backend.controller;


import com.enterprise.backend.model.Client;
import com.enterprise.backend.response.ClientDeleteResponse;
import com.enterprise.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping()
    public List<Client> getallClient() {

        return clientService.getallClient();
    }

    @PostMapping(value = "/signup")
    public ResponseEntity addClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }


    @GetMapping("/searchbyid")
    public Optional<Client> getClientByid(@RequestParam Long id) {

        return clientService.getClientByid(id);
    }

    @GetMapping("/searchbyname")
    public List<Client> getClientbyName(@RequestParam String name) {
        return clientService.getClientByname(name);

  }

    @DeleteMapping("/delete")
    public ClientDeleteResponse delete(@RequestParam Long id) {

        return clientService.delete(id);
    }

    @PutMapping("/update")
    public Client update(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @PostMapping("/deleteall")
    public String deleteall(){

        return clientService.deleteAllClient();
    }
}
