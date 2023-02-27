package com.enterprise.backend.DTO;

import com.enterprise.backend.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Idea_Cate_Request {

    private List<Category> categories;
    private long idea_id;
}
