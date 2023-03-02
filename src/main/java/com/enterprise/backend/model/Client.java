package com.enterprise.backend.model;


import com.enterprise.backend.response.FollowTopic;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

@SqlResultSetMapping(name = "Mapping.ClientTopic",classes = @ConstructorResult(targetClass = FollowTopic.class, columns = {
        @ColumnResult(name = "client_role"),
        @ColumnResult(name = "client_id"),
        @ColumnResult(name = "topic_name"),
        @ColumnResult(name = "image_url"),
        @ColumnResult(name = "topic_id")
}))

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_tbl")
public class Client {

    @Id
    private String id;

    @Column(name = "client_firstname", length = 45)
    private String firstname;

    @Column(name = "client_lastname", length = 45)
    private String lastname;

    @Column(name = "client_age", length = 45)
    private String age;

    @Column(name = "client_info")
    private String client_info;

    @Column(name = "client_role")
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "client_pronoun")
    @Enumerated(EnumType.STRING)
    private EPronoun pronoun;

    @Column(name = "client_isDeleted")
    private Boolean isDeleted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @JsonManagedReference(value = "client_department")
    private Department department;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client",fetch = FetchType.LAZY)
    @JsonManagedReference(value = "client_idea")
    private Set<Idea> ideas = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private Set<Reaction> reactions = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonManagedReference(value = "client_comment")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "client_id")
    @JsonManagedReference
    @JsonIgnore
    private Set<Client_Topic> clientTopics = new HashSet<>();


}
