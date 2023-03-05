package com.enterprise.backend.model;


import com.fasterxml.jackson.annotation.*;
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
@Embeddable
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


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonIncludeProperties({"name","id"})
    private Topic topic;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIncludeProperties({"firstname","lastname","id"})
    private Client client;

    @OneToMany(mappedBy = "idea",orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "idea_reaction")
    private Set<Reaction> reactions = new HashSet<>();


    @OneToMany(mappedBy = "idea",orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "idea_comment")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "idea_id",orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Idea_cate> idea_cate = new HashSet<>();


}
