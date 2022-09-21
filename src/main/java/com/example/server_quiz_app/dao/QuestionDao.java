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

    @Query("select category FROM Category category WHERE category.id=:id")
    public Teacher doesCategoryExist(@Param("id") Integer categoryId);

    @Query(
            nativeQuery = true,
            value = "SELECT q.* from question q join question_category qt ON q.question_id=qt.question_question_id join category c ON qt.category_category_id=c.category_id WHERE c.category_id=:id"
    )
    public List<Question> findQuestionsByCategory(@Param("id") Integer categoryId);
    //    SELECT q.* from category q join student_category qt ON q.category_id=qt.category_category_id join student c ON qt.student_student_id=c.student_id WHERE c.student_id=10;






}
