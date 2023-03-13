package com.enterprise.backend.DTO.Client;

import com.enterprise.backend.model.ERole;
import com.enterprise.backend.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateRequest {

    private String id;

    private String firstname;

    private String lastname;

    private String age;

    private String email;

    private String client_info;

    private ERole role;


}
