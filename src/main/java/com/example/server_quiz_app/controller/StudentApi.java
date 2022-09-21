package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.model.StudentCategoryReqBody;
import com.example.server_quiz_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentApi {

    @Autowired
    private StudentService studentService;


    @PostMapping("authenticate")
    public ResponseEntity<Response> authenticateStudent(@RequestBody Student student) throws Exception {
        return studentService.authenticateStudent(student);
    }
    @PostMapping("signup")
    public ResponseEntity<Response>  signUpStudent(@RequestBody Student student)  {
        return studentService.signUpStudent(student);
    }
    @PostMapping("save-student-categories")
    public ResponseEntity<Response>  saveStudentCategories(@RequestBody StudentCategoryReqBody studentCategoryReqBody)  {
        return studentService.addCategories(studentCategoryReqBody);
    }

}
