package com.enterprise.backend.DTO.Idea;

import com.enterprise.backend.model.Idea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdeaRequest {
    private String name;

    private String body;

    private Date date;

    private Date modify_date;

    private String attached_path;
    private String client_id;
    private Long topic_id;
}
