package com.example.server_quiz_app.service.student_service;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Post;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.request_models.AddComment;
import com.example.server_quiz_app.request_models.FollowTeacher;
import com.example.server_quiz_app.request_models.LikeQuestion;
import com.example.server_quiz_app.request_models.UserCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<Response> getStudents();
    ResponseEntity<Response> signUpStudent(Student student);
    ResponseEntity<Response> authenticateStudent(Student student);
    ResponseEntity<Response> addCategories(Integer studentId, List<Category> categories);
    ResponseEntity<Response> followTeacher(FollowTeacher body);
    ResponseEntity<Response> getFollowing(Integer studentId);
    ResponseEntity<Response> getCategories(Integer studentId);
    ResponseEntity<Response> likeQuestion(LikeQuestion body);

    ResponseEntity<Response> addComment(Integer studentId,AddComment body);
    ResponseEntity<Response> getPostByFollowing(Integer studentId);
    ResponseEntity<Response> getCommentsByPost(Integer postId);
    ResponseEntity<Response> likePost(Integer studentId,Integer postId);




}
