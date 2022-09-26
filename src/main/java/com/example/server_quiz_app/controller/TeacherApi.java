package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Teacher;
import com.example.server_quiz_app.request_models.UserCategory;
import com.example.server_quiz_app.service.teacher_service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherApi {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("teachers")
    public ResponseEntity<Response> getTeachers() {
        return teacherService.getTeachers();
    }

    @PostMapping("saveTeacher")
    public ResponseEntity<Response> saveTeacher(@RequestBody Teacher teacher){
        return teacherService.postTeacher(teacher);
    }

    @PostMapping("save-teacher-categories")
    public ResponseEntity<Response>  saveStudentCategories(@RequestBody UserCategory userCategory)  {
        return teacherService.addCategories(userCategory);
    }
}
