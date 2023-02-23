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

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cate_tbl")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "cate_name", length = 45)
    private String name;


    @ManyToMany(mappedBy = "category")
    //@JsonIgnoreProperties(value = "category")
    private Set<Idea> idea = new HashSet<>();

    @OneToMany(mappedBy = "cate_id")
    @JsonManagedReference
    private  Set<Idea_cate> idea_cate;

}
