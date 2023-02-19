package com.enterprise.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "topic_tbl")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "topic_name", length = 45)
    private String name;

    @Column(name = "topic_closure_date", length = 50)
    private String date;

    @Column(name = "topic_final_date", length = 50)
    private String finaldate;


    @OneToMany(mappedBy = "topic")
    private Set<Idea> ideas = new HashSet<>();
}
