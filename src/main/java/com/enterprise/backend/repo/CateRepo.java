package com.enterprise.backend.repo;

import com.enterprise.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CateRepo extends JpaRepository<Category,Long> {



}
