package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.request_models.GetQuestionByCategoryAndTypeRequest;
import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.service.question_service.QuestionService;
import com.example.server_quiz_app.service.question_service.QuestionServiceImpl;
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
    public ResponseEntity<Response> getQuestionsByCategoryAndType(
            @RequestParam Integer offset,
            @RequestParam Integer pageSize,
            @RequestBody GetQuestionByCategoryAndTypeRequest request
            ) {
        return questionService.getQuestionsByCategoryAndType(request,offset,pageSize);
    }

    @PostMapping("saveQuestion")
    public ResponseEntity<Response> saveQuestion(@RequestBody Question question){
        return questionService.postQuestion(question);
    }
}
