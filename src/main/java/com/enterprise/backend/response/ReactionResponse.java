package com.enterprise.backend.response;

import com.enterprise.backend.model.Client;
import com.enterprise.backend.model.Idea;
import com.enterprise.backend.model.Reaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "response_reaction_tbl")
public class ReactionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "upvote_count")
    private Long upvote;

/*
    @OneToMany(mappedBy = "reactionresponse")
    @JsonManagedReference
    private Set<Reaction> reactions = new HashSet<>();
*/

    @Column(name = "downvote_count")
    private Long downvote;

    @Column(name = "view_count")
    private Boolean view;
}
