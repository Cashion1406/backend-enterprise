package com.enterprise.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "comment")
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference(value = "client_comment")
    private Client client;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idea_id", referencedColumnName = "id")
    @JsonBackReference(value = "idea_comment")
    private Idea idea;


}
