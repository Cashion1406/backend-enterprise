package com.enterprise.backend.repo;

import com.enterprise.backend.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TopicRepo extends JpaRepository<Topic, Long> {

    @Query(value = "select t.topic_name from topic_tbl t where t.id = :id",nativeQuery = true)
    String getTopicname(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Topic t set t.isDeleted = true where t.id = :id")
    void softdeletetopic(@Param("id") Long id);

    List<Topic> findByisDeletedFalse();

    @Query("select t from Topic t where t.topic_closure_date < :date")
    List<Topic> getclosureTopic(@Param("date") String date);

}
