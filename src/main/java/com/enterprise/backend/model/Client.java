package com.enterprise.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client_tbl")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Department department;

    @OneToMany(mappedBy = "client")
    private Set<Idea> ideas = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Reaction> reactions = new HashSet<>();

    @OneToMany(mappedBy = "comment")
    private Set<Comment> comments = new HashSet<>();


}
