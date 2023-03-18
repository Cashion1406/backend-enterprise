package com.enterprise.backend.DTO.Idea;


import com.enterprise.backend.model.*;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
@AllArgsConstructor
@NoArgsConstructor
public class IdeaExportRequest {

    private long id;

    private String name;

    private String body;

    private String date;

    private String modify_date;

    private String attached_path;

    private String topic_name;

    private String client_name;

    private String category_name;

    private String client_id;

}
