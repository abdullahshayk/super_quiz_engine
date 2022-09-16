package com.example.server_quiz_app.dao;
import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface QuestionDao extends JpaRepository<Question,Integer>{

    public List<Question> findQuestionsByTeacherId(Integer teacherId);
    @Query("select teacher FROM Teacher teacher WHERE teacher.id=:id")
    public Teacher doesTeacherExist(@Param("id") Integer teacherId);




}
