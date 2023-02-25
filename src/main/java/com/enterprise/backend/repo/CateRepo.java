package com.enterprise.backend.repo;

import com.enterprise.backend.model.Category;
import com.enterprise.backend.model.Idea_cate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CateRepo extends JpaRepository<Category, Long> {


}
