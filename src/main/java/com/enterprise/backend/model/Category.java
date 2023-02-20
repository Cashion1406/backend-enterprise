package com.enterprise.backend.model;

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


    @ManyToMany()
    @JoinTable(name = "idea_cate_tbl",
    joinColumns = @JoinColumn(name = "idea_id"),
    inverseJoinColumns = @JoinColumn(name = "cate_id")
    )
    private Set<Idea> idea = new HashSet<>();
}
