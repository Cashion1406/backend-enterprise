package com.enterprise.backend.DTO;

import com.enterprise.backend.model.EPronoun;
import com.enterprise.backend.model.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDepartmentRequest {


    public String firstname;

    public String lastname;

    public String age;

    public String client_info;

    @Enumerated(EnumType.STRING)
    public ERole role;

    @Enumerated(EnumType.STRING)
    public EPronoun pronoun;

    public Boolean isDeleted;

    public long department_id;
}
