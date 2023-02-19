package com.enterprise.backend.service;



import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.ERole;
import com.enterprise.backend.model.Role;
import com.enterprise.backend.repo.ClientRepo;
import com.enterprise.backend.repo.RoleRepo;
import com.enterprise.backend.response.ClientDeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private RoleRepo roleRepo;

    public ResponseEntity saveClient(Client client) {

        if (clientRepo.existsByemail(client.getEmail())) {

            return ResponseEntity.badRequest().body("Email already used");

        }
        if (clientRepo.existsByname(client.getName())) {

                    return ResponseEntity.badRequest().body("Email already used");

                }



        Set<String> ok = client.getMockrole();
        Set<Role> roles = new HashSet<>();
        if (ok == null || ok.isEmpty()) {

            Role userrole = roleRepo.findByname(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("ROLE NTO FOUND"));
            roles.add(userrole);
        }
        else{

            ok.forEach(r -> {
                switch (r) {
                    case "admin" -> {
                        Role adminrole = roleRepo.findByname(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("ROLE NTO FOUND"));
                        roles.add(adminrole);
                    }
                    case "mod" -> {
                        Role modrole = roleRepo.findByname(ERole.ROLE_MOD).orElseThrow(() -> new RuntimeException("ROLE NTO FOUND"));
                        roles.add(modrole);
                    }
                    default -> {
                        Role userrole = roleRepo.findByname(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("ROLE NTO FOUND"));
                        roles.add(userrole);
                    }
                }

            });
        }
        client.setRoles(roles);
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
        Client existClient = clientRepo.findById(client.getId()).get();

        existClient.setName(client.getName());
        existClient.setEmail(client.getEmail());
        existClient.setClient_info(client.getClient_info());
        return clientRepo.save(existClient);
    }

    public Optional<Client> getClientByname(String name) {

        return clientRepo.findBynameContaining(name);
    }

    public String deleteAllClient() {
        clientRepo.deleteAll();
        return "SUCCESSFULLY DELETE ALL USER";
    }


}
