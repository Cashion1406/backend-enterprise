package com.enterprise.backend.model;


import com.enterprise.backend.DTO.Client.ClientNotification;
import com.enterprise.backend.DTO.Client.Client_Department_QA_DE;
import com.enterprise.backend.response.ClientReaction;
import com.enterprise.backend.response.FollowTopic;
import com.enterprise.backend.view.View;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@NamedNativeQuery(name = "Client.findfollowtopic",
        query = "select c.client_role as client_role, c.id as client_id, t.topic_name as topic_name, t.image_URL as image_url, t.id as topic_id from client_tbl c inner join follow_tbl f on c.id = f.client_id inner join topic_tbl t on f.topic_id = t.id where f.client_id =:client_id",
        resultSetMapping = "Mapping.ClientTopic"
)

@SqlResultSetMapping(name = "Mapping.ClientTopic", classes = @ConstructorResult(targetClass = FollowTopic.class, columns = {
        @ColumnResult(name = "client_role"),
        @ColumnResult(name = "client_id"),
        @ColumnResult(name = "topic_name"),
        @ColumnResult(name = "image_url"),
        @ColumnResult(name = "topic_id")
}))

@NamedNativeQuery(name = "Client.findClientReaction",
        query = "select r.id as reaction_id ,r.idea_id as idea_id , r.reaction as reaction from reaction_tbl r inner join client_tbl c on c.id = r.client_id where c.id=:id",
        resultSetMapping = "Mapping.ClientReaction"
)

@SqlResultSetMapping(name = "Mapping.ClientReaction", classes = @ConstructorResult(targetClass = ClientReaction.class, columns = {
        @ColumnResult(name = "idea_id"),
        @ColumnResult(name = "reaction_id"),
        @ColumnResult(name = "reaction")
}))

@NamedNativeQuery(name = "Client.findClientNotification",
        query = "select n.id as noti_id ,n.noti_content as content, n.noti_time as noti_time, n.noti_status as status from notification_tbl n where n.client_id = :id order by n.noti_time desc",
        resultSetMapping = "Mapping.ClientNotification"
)

@SqlResultSetMapping(name = "Mapping.ClientNotification", classes = @ConstructorResult(targetClass = ClientNotification.class, columns = {
        @ColumnResult(name = "noti_id"),
        @ColumnResult(name = "content"),
        @ColumnResult(name = "noti_time"),
        @ColumnResult(name = "status")
}))

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_tbl")
public class Client {

    @Id
    //  @JsonView(View.Sum.class)
    private String id;

    @Column(name = "client_firstname", length = 45)
    // @JsonView(View.Sum.class)
    private String firstname;

    @Column(name = "client_lastname", length = 45)
    //  @JsonView(View.Sum.class)
    private String lastname;

    @Column(name = "client_age", length = 45)
    // @JsonView(View.Sum.class)
    private String age;

    @Column(name = "client_info")
    //@JsonView(View.Sum.class)
    private String client_info;

    @Column(name = "client_role")
    @Enumerated(EnumType.STRING)
    //@JsonView(View.Sum.class)
    private ERole role;

    @Column(name = "client_pronoun")
    @Enumerated(EnumType.STRING)
    //@JsonView(View.Sum.class)
    private EPronoun pronoun;

    @Column(name = "client_email")
    private String email;

    @Column(name = "client_isDeleted")
    //@JsonView(View.Sum.class)
    private Boolean isDeleted;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @JsonManagedReference(value = "client_department")
    //@JsonView(View.SumwithDepartment.class)
    private Department department;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "client", orphanRemoval = true)
    @JsonManagedReference(value = "client_idea")
    private Set<Idea> ideas = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference
    //@JsonView(View.SumwithDepartment.class)
    private Set<Reaction> reactions = new HashSet<>();

    @OneToMany(mappedBy = "client", orphanRemoval = true,cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "client_comment")
    //@JsonView(View.SumwithDepartment.class)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "client_id", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    @JsonIgnore
    private Set<Client_Topic> clientTopics = new HashSet<>();


}
