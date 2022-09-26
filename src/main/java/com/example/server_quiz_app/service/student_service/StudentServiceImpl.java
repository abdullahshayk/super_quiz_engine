package com.example.server_quiz_app.service.student_service;

import com.example.server_quiz_app.dao.CategoryDao;
import com.example.server_quiz_app.dao.StudentDao;
import com.example.server_quiz_app.model.*;
import com.example.server_quiz_app.request_models.UserCategoryReqBody;
import com.example.server_quiz_app.security.JwtUtil;
import com.example.server_quiz_app.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private MyUserDetailService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private HttpStatus httpStatus;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public ResponseEntity<Response> getStudents() {
        List<Student> students=null;
        Response res=new Response();
        HttpStatus httpStatus=null;
        try{
            students= studentDao.findAll();
            res.setIsSuccessful(true);
            res.setMessage("Successful!");
            res.setData(students);
            httpStatus=HttpStatus.OK;
        }catch (Exception e){
            e.printStackTrace();
            res.setIsSuccessful(false);
            res.setMessage("Server Error!");
            res.setData(false);
            httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(res);

    }

    @Override
    public ResponseEntity<Response> signUpStudent(Student student) {
        Response response = new Response();
      try{
         // student.setPassword(passwordEncoder.encode(student.getPassword()));
          if(studentDao.findStudentByUsername(student.getUsername())!=null){
              response.setIsSuccessful(false);
              response.setMessage("User with username already exist");
              response.setData(false);
              httpStatus = HttpStatus.CONFLICT;
          }else {
              studentDao.save(student);
              response.setIsSuccessful(true);
              response.setMessage("Successful!");
              response.setData(true);
              httpStatus = HttpStatus.OK;
          }
      }
      catch (DataIntegrityViolationException e){
          e.printStackTrace();
          response.setIsSuccessful(false);
          response.setMessage("username, email and password cannot be empty");
          response.setData(false);
          httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
      }
      catch (Exception e){
          e.printStackTrace();
          response.setIsSuccessful(false);
          response.setMessage("Server Error!");
          response.setData(false);
          httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
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

        response.setMessage("Login Successful");
        response.setIsSuccessful(true);
        response.setData(jwt);
        return ResponseEntity.ok(response);
    }
    @Override
    public ResponseEntity<Response> addCategories(UserCategoryReqBody body) {
        Response response = new Response();
        try{
            Student student=studentDao.findStudentByUsername(body.getUsername());
            if(student==null){
                response.setIsSuccessful(false);
                response.setMessage("User with this username not found!");
                response.setData(null);
                httpStatus=HttpStatus.NOT_FOUND;
            }
            else{
                student.setCategories(body.getCategoryList());
                studentDao.save(student);
                response.setIsSuccessful(true);
                response.setMessage("Categories saved");
                response.setData(true);
                httpStatus=HttpStatus.OK;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @Override
    public ResponseEntity<Response> followTeacher(UserCategoryReqBody body) {
        Response response = new Response();
        try{
            Student student=studentDao.findStudentByUsername(body.getUsername());
            if(student==null){
                response.setIsSuccessful(false);
                response.setMessage("User with this username not found!");
                response.setData(null);
                httpStatus=HttpStatus.NOT_FOUND;
            }
            else{
                student.setCategories(body.getCategoryList());
                studentDao.save(student);
                response.setIsSuccessful(true);
                response.setMessage("Categories saved");
                response.setData(true);
                httpStatus=HttpStatus.OK;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }









}
