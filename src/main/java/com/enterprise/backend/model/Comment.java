package com.enterprise.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment_tbl")
@Embeddable
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "comment_body")
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id","lastname","firstname"})
    private Client client;


    @Column(name = "anonymous")
    private Boolean anonymous;


    @Column(name = "modify_date")
    private String modify_date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idea_id", referencedColumnName = "id")
    @JsonBackReference(value = "idea_comment")
    private Idea idea;


}
