package com.enterprise.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "idea_cate_tbl")
public class Idea_cate {

    @Id
    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "cate_id",referencedColumnName = "id")
    @JsonIncludeProperties("name")
    private Category cate_id;

    @Id
    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "idea_id",referencedColumnName = "id")
    @JsonBackReference
    private Idea idea_id;
}
