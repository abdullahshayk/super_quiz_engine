package com.example.server_quiz_app.controller;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Post;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.request_models.AddComment;
import com.example.server_quiz_app.request_models.FollowTeacher;
import com.example.server_quiz_app.request_models.LikeQuestion;
import com.example.server_quiz_app.request_models.UserCategory;
import com.example.server_quiz_app.service.student_service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentApi {

    @Autowired
    private StudentService studentService;

    @GetMapping("students")
    public ResponseEntity<Response> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("authenticate")
    public ResponseEntity<Response> authenticateStudent(@RequestBody Student student) throws Exception {
        return studentService.authenticateStudent(student);
    }
    @PostMapping("signup")
    public ResponseEntity<Response>  signUpStudent(@RequestBody Student student)  {
        return studentService.signUpStudent(student);
    }
    @PostMapping("save-student-categories/{id}")
    public ResponseEntity<Response>  saveStudentCategories(
            @PathVariable Integer id,
            @RequestBody List<Category> categories) {
        return studentService.addCategories(id,categories);
    }
    @PostMapping("follow-teacher")
    public ResponseEntity<Response>  followTeacher(@RequestBody FollowTeacher body)  {
        return studentService.followTeacher(body);
    }

    @PostMapping("like-question")
    public ResponseEntity<Response>  likeQuestion(@RequestBody LikeQuestion body)  {
        return studentService.likeQuestion(body);
    }

    @GetMapping("student-following/{id}")
    public ResponseEntity<Response> getFollowing(@PathVariable Integer id) {
        return studentService.getFollowing(id);
    }

    @GetMapping("student-categories/{id}")
    public ResponseEntity<Response> getCategoryOfStudent(@PathVariable int id) {
        return studentService.getCategories(id);
    }

    @PostMapping("add-comment/{studentId}")
    public ResponseEntity<Response> addComment(
            @PathVariable int studentId,
            @RequestBody AddComment body
            ) {
        return studentService.addComment(studentId,body);
    }

    @GetMapping("get-post-by-followings/{studentId}")
    public ResponseEntity<Response> getPostByFollowings(
            @PathVariable int studentId
    ) {
        return studentService.getPostByFollowing(studentId);
    }

    @GetMapping("get-comments-by-post/{postId}")
    public ResponseEntity<Response> getCommentsByPost(
            @PathVariable int postId
    ) {
        return studentService.getCommentsByPost(postId);
    }

    @GetMapping("like-post/{studentId}/{postId}")
    public ResponseEntity<Response>  likePost(
            @PathVariable int studentId,
            @PathVariable int postId
    )  {
        return studentService.likePost(studentId,postId);
    }



}
