package com.enterprise.backend.repo;

import com.enterprise.backend.model.Idea;
import com.enterprise.backend.model.Idea_cate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface IdeaRepo extends JpaRepository<Idea, Long> {

    @Query("select i from Idea i where i.id = :id  ")
    List<Idea> getideabyid(@Param("id") String id);

    @Query("select size(i.reactions) from Idea i where i.id=:id")
    int gettotalview(@Param("id") Long id);


    @Query(value = "select i.id,id.attached_path, i.idea_body, i.client_id, i.topic_id, r.id, r.client_id, r.idea_id from idea_tbl i inner join reaction_tbl r on r.client_id = i.client_id where r.reaction = true", nativeQuery = true)
    List<Idea> getupvote();

    @Query(value = "select i.id,id.attached_path, i.idea_body, i.client_id, i.topic_id, r.id, r.client_id, r.idea_id from idea_tbl i inner join reaction_tbl r on r.client_id = i.client_id where r.reaction = false", nativeQuery = true)
    List<Idea> getdownvote();


    @Query(value = "insert into idea_cate_tbl (cate_id,idea_id) values (:cate_id,:idea_id)", nativeQuery = true)
    String insertideacatev2(@Param("cate_id") long cate_id, @Param("idea_id") long idea_id);
}
