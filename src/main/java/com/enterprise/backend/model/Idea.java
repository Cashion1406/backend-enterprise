package com.enterprise.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "idea_tbl")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "idea_title")
    private String name;

    @Column(name = "idea_body")
    private String body;

    @Column(name = "date")
    private Date date;

    @Column(name = "modify_date")
    private Date modify_date;

    @Column(name = "attached_path")
    private String attached_path;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonBackReference
    private Topic topic;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference(value = "client_idea")
    private Client client;

    @OneToMany(mappedBy = "idea")
    private Set<Reaction> reactions = new HashSet<>();


    @OneToMany(mappedBy = "idea_id")
    @JsonManagedReference
    private Set<Idea_cate> idea_cate;

}
