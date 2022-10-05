package com.example.server_quiz_app.service.teacher_service;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Post;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Teacher;
import com.example.server_quiz_app.request_models.UserCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {
    ResponseEntity<Response> getTeachers();

    ResponseEntity<Response> postTeacher(Teacher teacher);

    ResponseEntity<Response> addCategories(Integer teacherId, List<Category> categories);

    ResponseEntity<Response> addCategories(UserCategory body);

    ResponseEntity<Response> getCategories(Integer teacherId);

    ResponseEntity<Response> addPost(Integer teacherId,Post post);



}
