package com.enterprise.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = "client_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = "client_email"),
        @UniqueConstraint(columnNames = "client_name")})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "client_name", length = 45)
    private String name;

    @Column(name = "client_email", length = 50)
    private String email;

    @Column(name = "client_info")
    private String client_info;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role_tbl", joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Transient
    private Set<String> mockrole;

}
