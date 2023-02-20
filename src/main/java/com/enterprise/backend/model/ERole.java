package com.enterprise.backend.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
public enum ERole {
    ROLE_USER,
    ROLE_MOD,
    ROLE_ADMIN,
    ROLE_QA_DE,
    ROLE_QA_ALL
}
