package com.enterprise.backend.repo;

import com.enterprise.backend.DTO.Client.ClientNotification;
import com.enterprise.backend.DTO.Client.Client_Department_QA_DE;
import com.enterprise.backend.model.ERole;
import com.enterprise.backend.response.ClientReaction;
import com.enterprise.backend.response.FollowTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.enterprise.backend.model.Client;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, String> {

    @Query("select c from Client c where c.department.id = :id and c.role = 'ROLE_QA_DE'")
    Client getClientQA(@Param("id") Long id);

    @Query("select c from Client c inner join Client_Topic f on c.id = f.client_id.id where f.topic_id.id = :id")
    List<Client> getAllClientWithTopic(@Param("id") Long id);

    List<Client> findBylastnameContaining(String firstname);

    @Query(value = "insert into follow_tbl values (:topic_id, :client_id)", nativeQuery = true)
    void insertfollowtopic(@Param("topic_id") long topic_id, @Param("client_id") String client_id);

    @Transactional
    @Modifying
    @Query(value = "delete from follow_tbl f where f.topic_id =:topic_id and f.client_id =:client_id",nativeQuery = true)
    void removeFollowTopic(@Param("topic_id") Long topic_id,@Param("client_id") String client_id);

    @Query(value = "select c.client_lastname from client_tbl c where c.id = :id", nativeQuery = true)
    String getClientLastName(@Param("id") String id);

    @Query(value = "select c.client_firstname from client_tbl c where c.id = :id", nativeQuery = true)
    String getClientFirstName(String id);

    @Query(nativeQuery = true)
    List<FollowTopic> findfollowtopic(String client_id);

    @Query(nativeQuery = true)
    List<ClientReaction> findClientReaction(String id);


    @Query(value = "select r.idea_id as idea_id , r.reaction as reaction from reaction_tbl r inner join client_tbl c on c.id = r.client_id where c.id=:id", nativeQuery = true)
    List<ClientReaction> findclientreaction(@Param("id") String id);

    //Pending
    @Query(value = "select c.client_role as client_role, c.id as client_id, t.topic_name as topic_name, t.image_URL as image_url, t.id as topic_id from client_tbl c inner join follow_tbl f on c.id = f.client_id inner join topic_tbl t on f.topic_id = t.id where f.client_id =:client_id", nativeQuery = true)
    List<FollowTopic> findv2(@Param("client_id") String client_id);

    @Query(nativeQuery = true)
    List<ClientNotification> findClientNotification(@Param("id") String id);

}
