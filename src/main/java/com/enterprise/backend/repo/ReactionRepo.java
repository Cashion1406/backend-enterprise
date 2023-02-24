package com.enterprise.backend.repo;

import com.enterprise.backend.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReactionRepo extends JpaRepository<Reaction,Long> {

}
