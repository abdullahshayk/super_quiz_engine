package com.example.server_quiz_app.service.student_service;

import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.request_models.UserCategoryReqBody;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<Response> getStudents();
    ResponseEntity<Response> signUpStudent(Student student);
    ResponseEntity<Response> authenticateStudent(Student student);
    ResponseEntity<Response> addCategories(UserCategoryReqBody body);
    ResponseEntity<Response> followTeacher(UserCategoryReqBody body);
    }
