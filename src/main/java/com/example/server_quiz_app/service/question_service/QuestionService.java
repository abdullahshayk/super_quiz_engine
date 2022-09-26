package com.example.server_quiz_app.service.question_service;

import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.request_models.GetQuestionByCategoryAndType;
import org.springframework.http.ResponseEntity;

public interface QuestionService {
    ResponseEntity<Response> getQuestions();

    ResponseEntity<Response> postQuestion(Question question);

    ResponseEntity<Response> getQuestionOfSpecificTeacher(Integer teacherId);
    ResponseEntity<Response> getQuestionsByCategoryAndType(GetQuestionByCategoryAndType request, Integer offset, Integer pageSize);


    }
