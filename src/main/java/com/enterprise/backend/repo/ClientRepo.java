package com.enterprise.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.enterprise.backend.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    Optional<Client> findBynameContaining(String name);

    Boolean existsByemail(String email);
    Boolean existsByname(String name);

}
