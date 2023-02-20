package com.enterprise.backend.model;

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
@Table(name = "reaction_tbl")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idea_id", referencedColumnName = "id")
    private Idea idea;

    @Column(name = "reaction")
    private Boolean reaction;
}
