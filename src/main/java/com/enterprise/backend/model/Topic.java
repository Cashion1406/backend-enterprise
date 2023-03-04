package com.enterprise.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "topic_tbl")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "topic_name", length = 45)
    private String name;

    @Column(name = "topic_closure_date", length = 50)
    private String idea_closure_date;

    @Column(name = "topic_final_date", length = 50)
    private String final_closure_date;

    @Column(name ="modify_date")
    private String modifyDate;
    @Column(name ="isDeleted")
    private Boolean isDeleted;

    @Column(name = "image_url")
    private String imageURL;

    @OneToMany(mappedBy = "topic", fetch =  FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "idea_topic")
    private Set<Idea> ideas = new HashSet<>();

    @OneToMany(mappedBy = "topic_id")
    @JsonManagedReference
    @JsonIgnore
    private Set<Client_Topic> clientTopics = new HashSet<>();
}
