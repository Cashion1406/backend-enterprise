package com.enterprise.backend.model;


import com.enterprise.backend.view.View;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "department_tbl")
public class    Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.SumwithDepartment.class)
    private long id;


    @Column(name = "depart_name", length = 30)
    @JsonView(View.SumwithDepartment.class)
    private String name;

    @Column(name = "department_info")
    @JsonView(View.SumwithDepartment.class)
    private String department_info;

    @Column(name = "isDeleted")
    @JsonView(View.SumwithDepartment.class)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    @JsonBackReference(value = "client_department")
    @JsonView(View.SumwithDepartment.class)
    @JsonIgnore
    private Set<Client> clients = new HashSet<>();

}
