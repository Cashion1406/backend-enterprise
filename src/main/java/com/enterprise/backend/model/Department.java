package com.enterprise.backend.model;


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
@Table(name = "department_tbl")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "depart_name", length = 30)
    private String name;

    @Column(name = "department_info")
    private String client_info;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Client> clients = new HashSet<>();

}
