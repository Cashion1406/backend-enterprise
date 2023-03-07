package com.enterprise.backend.DTO.Idea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdeaUpdateRequest {

    private String name;

    private String body;

    private String date;

    private String modify_date;

    private String attached_path;

    private Boolean anonymous;
}
