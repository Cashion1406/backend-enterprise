package com.enterprise.backend.DTO.Reaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReactionUpdateRequest {

    private long reaction_id;

    private Boolean reaction;


}
