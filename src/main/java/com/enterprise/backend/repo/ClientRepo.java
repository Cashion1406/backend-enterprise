package com.enterprise.backend.repo;

import com.enterprise.backend.response.ClientReaction;
import com.enterprise.backend.response.FollowTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.enterprise.backend.model.Client;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, String> {
    List<Client> findBylastnameContaining(String firstname);

    @Query(value = "insert into follow_tbl values (:topic_id, :client_id)",nativeQuery = true)
    void insertfollowtopic (@Param("topic_id") long topic_id, @Param("client_id") String client_id);

    @Query(value = "select c.client_lastname from client_tbl c where c.id = :id",nativeQuery = true)
    String getClientname(@Param("id") String id);


    @Query( nativeQuery = true)
     List <FollowTopic> findfollowtopic ( String client_id);

    @Query(nativeQuery = true)
    List<ClientReaction> findClientReaction(String id);



    @Query(value = "select r.idea_id as idea_id , r.reaction as reaction from reaction_tbl r inner join client_tbl c on c.id = r.client_id where c.id=:id",nativeQuery = true)
    List<ClientReaction> findclientreaction(@Param("id") String id);
    //Pending
    @Query(value = "select c.client_role as client_role, c.id as client_id, t.topic_name as topic_name, t.image_URL as image_url, t.id as topic_id from client_tbl c inner join follow_tbl f on c.id = f.client_id inner join topic_tbl t on f.topic_id = t.id where f.client_id =:client_id",nativeQuery = true)
    List<FollowTopic> findv2(@Param("client_id") String client_id);


}
