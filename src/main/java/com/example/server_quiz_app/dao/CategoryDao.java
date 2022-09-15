package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer>{

}
