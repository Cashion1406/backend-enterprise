package com.enterprise.backend.repo;

import com.enterprise.backend.model.ERole;
import com.enterprise.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByname(ERole name);
}
