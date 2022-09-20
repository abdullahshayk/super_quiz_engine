package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryApi {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories")
    public ResponseEntity<Response> getCategories() {
        return categoryService.getCategories();
    }
}
