package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Post;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Teacher;
import com.example.server_quiz_app.request_models.UserCategory;
import com.example.server_quiz_app.service.teacher_service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherApi {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("teachers")
    public ResponseEntity<Response> getTeachers() {
        return teacherService.getTeachers();
    }

    @PostMapping("saveTeacher")
    public ResponseEntity<Response> saveTeacher(@RequestBody Teacher teacher) {
        return teacherService.postTeacher(teacher);
    }

    @PostMapping("save-teacher-categories/{id}")
    public ResponseEntity<Response> saveTeacherCategories(
            @PathVariable Integer id,
            @RequestBody List<Category> categories) {
        return teacherService.addCategories(id,categories);
    }

    @GetMapping("teacher-category/{id}")
    public ResponseEntity<Response> getCategoryOfTeacher(@PathVariable int id) {
        return teacherService.getCategories(id);
    }

    @PostMapping("add-post/{id}")
    public ResponseEntity<Response> addPost(
            @PathVariable int id,
            @RequestBody Post post
            ) {
        return teacherService.addPost(id,post);
    }
}
