package com.example.server_quiz_app.service;

import com.example.server_quiz_app.dao.QuestionDao;
import com.example.server_quiz_app.dao.StudentDao;
import com.example.server_quiz_app.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;


}
