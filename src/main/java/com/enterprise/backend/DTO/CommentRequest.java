package com.enterprise.backend.DTO;

import com.enterprise.backend.model.Comment;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {


    private String comment;
    private Long idea_id;

    private String client_id;

    private Boolean anonymous;
}
