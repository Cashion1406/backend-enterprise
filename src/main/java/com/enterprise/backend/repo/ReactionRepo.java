package com.enterprise.backend.repo;

import com.enterprise.backend.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepo extends JpaRepository<Reaction,Long> {

    @Query(value = "select * from reaction_tbl r where r.client_id = :client_id and r.idea_id=:idea_id ",nativeQuery = true)
    Reaction getReaction (@Param("client_id") String client_id, @Param("idea_id") long idea_id);

    @Query(value = "listen idea_notify", nativeQuery = true)
    String notifyIdea();
}
