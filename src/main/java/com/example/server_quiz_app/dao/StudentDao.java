package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentDao extends JpaRepository<Student,Integer> {

    Student findStudentByUsername(String username);


}
