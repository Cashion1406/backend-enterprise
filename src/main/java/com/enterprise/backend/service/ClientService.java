package com.enterprise.backend.service;



import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.ERole;
import com.enterprise.backend.repo.ClientRepo;
import com.enterprise.backend.response.ClientDeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;


    public ResponseEntity saveClient(Client client) {
        return ResponseEntity.ok(clientRepo.save(client));
    }

    public List<Client> getallClient() {

        return clientRepo.findAll();
    }

    public Optional<Client> getClientByid(long id) {

        return clientRepo.findById(id);
    }


    public ClientDeleteResponse delete(long id) {
        clientRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new ClientDeleteResponse("Deleted user " + id, timestamp, true);
    }

    public Client updateClient(Client client) {
        Client existClient = clientRepo.findById(Long.valueOf(client.getId())).get();
        existClient.setClient_info(client.getClient_info());
        return clientRepo.save(existClient);
    }

/*    public Optional<Client> getClientByname(String firstname) {

        return clientRepo.findBynameContaining(firstname);
    }*/

    public String deleteAllClient() {
        clientRepo.deleteAll();
        return "SUCCESSFULLY DELETE ALL USER";
    }


}
