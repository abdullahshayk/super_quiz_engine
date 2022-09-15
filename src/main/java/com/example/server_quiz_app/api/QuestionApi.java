package com.example.server_quiz_app.api;

import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QuestionApi {
    @Autowired
    private QuestionService questionService;

    @GetMapping("questions")
    public ResponseEntity<Response> getQuestions() {
        return questionService.getQuestions();
    }

}
