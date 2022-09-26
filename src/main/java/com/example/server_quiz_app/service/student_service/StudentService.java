package com.example.server_quiz_app.service.student_service;

import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.request_models.FollowTeacher;
import com.example.server_quiz_app.request_models.UserCategory;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<Response> getStudents();
    ResponseEntity<Response> signUpStudent(Student student);
    ResponseEntity<Response> authenticateStudent(Student student);
    ResponseEntity<Response> addCategories(UserCategory body);
    ResponseEntity<Response> followTeacher(FollowTeacher body);
    }
