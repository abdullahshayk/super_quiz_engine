package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.service.QuestionService;
import net.bytebuddy.build.RepeatedAnnotationPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class QuestionApi {
    @Autowired
    private QuestionService questionService;

    @GetMapping("questions")
    public ResponseEntity<Response> getQuestions() {
        return questionService.getQuestions();
    }
    @GetMapping("questions-by-teacher")
    public ResponseEntity<Response> getQuestionsByTeacher(@RequestParam Integer teacherId) {
        return questionService.getQuestionOfSpecificTeacher(teacherId);
    }
    @GetMapping("questions-by-category")
    public ResponseEntity<Response> getQuestionsByCategory(@RequestParam Integer categoryId) {
        return questionService.getQuestionByCategory(categoryId);
    }
    @PostMapping("saveQuestion")
    public ResponseEntity<Response> saveQuestion(@RequestBody Question question){
        return questionService.postQuestion(question);
    }
}
