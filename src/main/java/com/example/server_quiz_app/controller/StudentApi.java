package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.request_models.FollowTeacher;
import com.example.server_quiz_app.request_models.UserCategory;
import com.example.server_quiz_app.service.student_service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentApi {

    @Autowired
    private StudentService studentService;

    @GetMapping("students")
    public ResponseEntity<Response> getTeachers() {
        return studentService.getStudents();
    }

    @PostMapping("authenticate")
    public ResponseEntity<Response> authenticateStudent(@RequestBody Student student) throws Exception {
        return studentService.authenticateStudent(student);
    }
    @PostMapping("signup")
    public ResponseEntity<Response>  signUpStudent(@RequestBody Student student)  {
        return studentService.signUpStudent(student);
    }
    @PostMapping("save-student-categories")
    public ResponseEntity<Response>  saveStudentCategories(@RequestBody UserCategory userCategory)  {
        return studentService.addCategories(userCategory);
    }
    @PostMapping("follow-teacher")
    public ResponseEntity<Response>  followTeacher(@RequestBody FollowTeacher body)  {
        return studentService.followTeacher(body);
    }

}
