package com.example.server_quiz_app.service.category_service;

import com.example.server_quiz_app.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface CategoryService {
    ResponseEntity<Response> getCategories();
//    ResponseEntity<Response> getCategoriesOfStudent(int id);
//    ResponseEntity<Response> getCategoriesOfTeacher(int id);

    }
