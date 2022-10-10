package com.example.server_quiz_app.service.student_service;

import com.example.server_quiz_app.dao.*;
import com.example.server_quiz_app.exception_handling.AlreadyExistsException;
import com.example.server_quiz_app.exception_handling.ResourceNotFoundException;
import com.example.server_quiz_app.model.*;
import com.example.server_quiz_app.request_models.AddComment;
import com.example.server_quiz_app.request_models.FollowTeacher;
import com.example.server_quiz_app.request_models.LikeQuestion;
import com.example.server_quiz_app.security.JwtUtil;
import com.example.server_quiz_app.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserDetailService userDetailsService;
    @Autowired
    private PostDao postDao;
    private HttpStatus httpStatus;

    @Override
    public ResponseEntity<Response> getStudents() {
        List<Student> students = null;
        Response res = new Response();
        HttpStatus httpStatus = null;
        try {
            students = studentDao.findAll();
            res.setIsSuccessful(true);
            res.setMessage("Successful!");
            res.setData(students);
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            res.setIsSuccessful(false);
            res.setMessage("Server Error!");
            res.setData(false);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(res);

    }

    @Override
    public ResponseEntity<Response> signUpStudent(Student student) {
        Response response = new Response();
        try {
            if (studentDao.findStudentByUsername(student.getUsername()) != null) {
                response.setIsSuccessful(false);
                response.setMessage("User with username already exist");
                response.setData(false);
                httpStatus = HttpStatus.CONFLICT;
            } else {
                student.setPassword(passwordEncoder.encode(student.getPassword()));
                studentDao.save(student);
                response.setIsSuccessful(true);
                response.setMessage("Successful!");
                response.setData(true);
                httpStatus = HttpStatus.OK;
            }
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("username, email and password cannot be empty");
            response.setData(false);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @Override
    public ResponseEntity<Response> authenticateStudent(Student student) {
        Response response = new Response();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            student.getUsername(),
                            student.getPassword()
                    )
            );

        } catch (BadCredentialsException e) {
            response.setMessage("Invalid Credentials");
            response.setIsSuccessful(false);
            response.setData(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(student.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        response.setIsSuccessful(true);
        response.setData(new HashMap<String, String>() {{
            put("token", jwt);
            put("expirationDate", jwtTokenUtil.getExpirationDateFromToken(jwt).toString());
        }});
        return ResponseEntity.ok(response);

    }

    @Override
    public ResponseEntity<Response> addCategories(Integer studentId, List<Category> categories) {
        Response response = new Response();
        try {
            Optional<Student> student = studentDao.findById(studentId);
            if (!student.isPresent()) {
                response.setIsSuccessful(false);
                response.setMessage("Student not found!");
                response.setData(null);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                Student studentToUpdate = student.get();
                studentToUpdate.setCategories(categories);
                studentDao.save(studentToUpdate);
                response.setIsSuccessful(true);
                response.setMessage("Categories saved");
                response.setData(true);
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @Override
    public ResponseEntity<Response> followTeacher(FollowTeacher body) {
        Response response = new Response();
        try {
            Optional<Student> student = studentDao.findById(body.getStudentId());
            if (!student.isPresent()) {
                response.setIsSuccessful(false);
                response.setMessage("Student does not Exists!");
                response.setData(null);
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                Optional<Teacher> teacher = teacherDao.findById(body.getTeacherId());
                if (teacher.isPresent()) {
                    Student studentToUpdate = student.get();
                    List<Teacher> teachersTemp = studentToUpdate.getFollowedTeachers();
                    teachersTemp.add(teacher.get());
                    studentToUpdate.setFollowedTeachers(teachersTemp);
                    studentDao.save(studentToUpdate);
                    response.setIsSuccessful(true);
                    response.setMessage("Teacher Followed");
                    response.setData(true);
                    studentToUpdate.getFollowedTeachers().get(0).getName();
                    httpStatus = HttpStatus.OK;

                } else {
                    response.setIsSuccessful(false);
                    response.setMessage("Teacher does not Exists!");
                    response.setData(null);
                    httpStatus = HttpStatus.NOT_FOUND;
                }
            }
        } catch (DataIntegrityViolationException e) {
            response.setIsSuccessful(false);
            response.setMessage("Teacher Already Followed");
            response.setData(null);
            httpStatus = HttpStatus.CONFLICT;
        } catch (Exception e) {
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @Override
    public ResponseEntity<Response> getFollowing(Integer studentId) {
        Optional<Student> student = null;
        Response response = new Response();
        HttpStatus httpStatus = null;
        try {
            student = studentDao.findById(studentId);
            if (student.isPresent()) {
                response.setIsSuccessful(true);
                response.setMessage("Successful!");
                response.setData(student.get().getFollowedTeachers());
                httpStatus = HttpStatus.OK;
            } else {
                response.setIsSuccessful(false);
                response.setMessage("Student does not Exists!");
                response.setData(null);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @Override
    public ResponseEntity<Response> getCategories(Integer studentId) {
        Optional<Student> student = null;
        Response response = new Response();
        HttpStatus httpStatus = null;
        try {
            student = studentDao.findById(studentId);
            if (student.isPresent()) {
                response.setIsSuccessful(true);
                response.setMessage("Successful!");
                response.setData(student.get().getCategories());
                httpStatus = HttpStatus.OK;
            } else {
                response.setIsSuccessful(false);
                response.setMessage("Student does not Exists!");
                response.setData(null);
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }


    @Override
    public ResponseEntity<Response> addComment(Integer studentId, AddComment body) {
        Response response = new Response();
        Optional<Student> student = studentDao.findById(studentId);
        Optional<Post> post = postDao.findById(body.getPostId());
        if (!student.isPresent()) {
            throw new ResourceNotFoundException("Student Not found");
        } else if (!post.isPresent()) {
            throw new ResourceNotFoundException("Post Not found");
        } else {
            Comment comment = body.getComment();
            comment.setPost(post.get());
            comment.setStudent(student.get());
            commentDao.save(comment);
            response.setIsSuccessful(true);
            response.setMessage("Comment Published");
            httpStatus = HttpStatus.CREATED;
            return ResponseEntity.status(httpStatus).body(response);
        }


    }

    @Override
    public ResponseEntity<Response> getPostByFollowing(Integer studentId) {
        Response response = new Response();
        Optional<Student> student = studentDao.findById(studentId);
        if(!student.isPresent()){
            throw new ResourceNotFoundException("Student Not found");
        }
        else{
            List<Post> posts = student.get().getFollowedTeachers().stream()
                    .flatMap(e -> e.getPosts().stream())
                    .collect(Collectors.toList());
            response.setIsSuccessful(true);
            response.setData(posts);
            response.setMessage("Successful");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    public ResponseEntity<Response> getCommentsByPost(Integer postId) {
        Response response = new Response();
        Optional<Post> post = postDao.findById(postId);
        if(!post.isPresent()){
            throw new ResourceNotFoundException("Post Not found");
        }
        else{
            List<Comment> comments = post.get().getComments();
            response.setIsSuccessful(true);
            response.setData(comments);
            response.setMessage("Successful");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @Override
    public ResponseEntity<Response> likeQuestion(LikeQuestion body) {
        Response response = new Response();
            Optional<Student> student = studentDao.findById(body.getStudentId());
            Optional<Question> question = questionDao.findById(body.getQuestionId());
            if(!student.isPresent()){
                throw new ResourceNotFoundException("Student Not found");
            } else if(!question.isPresent()){
                throw new ResourceNotFoundException("Question Not found");
            }
            else {
                Student studentToUpdate = student.get();
                List<Question> questionsTemp = studentToUpdate.getLikedQuestion();
                if(questionsTemp.contains(question.get())){
                    throw new AlreadyExistsException("Question already liked");
                }
                questionsTemp.add(question.get());
                studentToUpdate.setLikedQuestion(questionsTemp);
                studentDao.save(studentToUpdate);
                response.setIsSuccessful(true);
                response.setMessage("Question Liked");
                response.setData(true);
                httpStatus = HttpStatus.OK;
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

    }

    @Override
    public ResponseEntity<Response> likePost(Integer studentId, Integer postId) {
        Response response = new Response();
        Optional<Student> student=studentDao.findById(studentId);
        Optional<Post> post = postDao.findById(postId);
        if(!student.isPresent()){
            throw new ResourceNotFoundException("Student Not found");
        } else if (!post.isPresent()) {
            throw new ResourceNotFoundException("Post Not found");
        }
        else{
            Student studentToUpdate=student.get();
            List<Post> likedPosts = student.get().getLikedPosts();
            if(likedPosts.contains(post.get())){
                throw new AlreadyExistsException("Post already liked");
            }
            likedPosts.add(post.get());
            studentToUpdate.setLikedPosts(likedPosts);
            studentDao.save(studentToUpdate);
            response.setIsSuccessful(true);
            response.setData(true);
            response.setMessage("Post Liked");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


}
