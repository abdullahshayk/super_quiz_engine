package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentApi {

    @Autowired
    private StudentService studentService;


    @PostMapping("authenticate")
    public ResponseEntity<Response> authenticateStudent(@RequestBody Student student) throws Exception {
        return studentService.authenticateStudent(student);
    }
}
