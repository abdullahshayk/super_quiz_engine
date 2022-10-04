package com.example.server_quiz_app.service.student_service;

import com.example.server_quiz_app.dao.StudentDao;
import com.example.server_quiz_app.dao.TeacherDao;
import com.example.server_quiz_app.model.*;
import com.example.server_quiz_app.request_models.FollowTeacher;
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

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserDetailService userDetailsService;
    private HttpStatus httpStatus;

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
            if(studentDao.findStudentByUsername(student.getUsername())!=null){
                response.setIsSuccessful(false);
                response.setMessage("User with username already exist");
                response.setData(false);
                httpStatus = HttpStatus.CONFLICT;
            }else {
                student.setPassword(passwordEncoder.encode(student.getPassword()));
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
            response.setIsSuccessful(true);
            response.setData(new HashMap<String, String>() {{
                put("token", jwt);
                put("expirationDate", jwtTokenUtil.getExpirationDateFromToken(jwt).toString());
            }});
            return ResponseEntity.ok(response);

//        try {
//            final UserDetails userDetails = userDetailsService.loadUserByUsername(student.getUsername());
//            if(!Objects.equals(EncryptionAndDecryption.decrypt(userDetails.getPassword()), student.getPassword())) {
//                throw new UsernameNotFoundException("Invalid Password");
//            }
//            final String jwt = jwtTokenUtil.generateToken(userDetails);
//            response.setMessage("Login Successful");
//            response.setIsSuccessful(true);
//            response.setData(jwt);
//            return ResponseEntity.ok(response);
//        }
//        catch (UsernameNotFoundException e) {
//            response.setMessage(e.getMessage());
//            response.setIsSuccessful(false);
//            response.setData(false);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
    }
    @Override
    public ResponseEntity<Response> addCategories(Integer studentId, List<Category> categories) {
        Response response = new Response();
        try{
            Optional<Student> student=studentDao.findById(studentId);
            if(!student.isPresent()){
                response.setIsSuccessful(false);
                response.setMessage("Student not found!");
                response.setData(null);
                httpStatus=HttpStatus.NOT_FOUND;
            }
            else{
                Student studentToUpdate=student.get();
                studentToUpdate.setCategories(categories);
                studentDao.save(studentToUpdate);
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
    public ResponseEntity<Response> followTeacher(FollowTeacher body) {
        Response response = new Response();
        try{
            Optional<Student> student=studentDao.findById(body.getStudentId());
            if(!student.isPresent()){
                response.setIsSuccessful(false);
                response.setMessage("Student does not Exists!");
                response.setData(null);
                httpStatus=HttpStatus.NOT_FOUND;
            }
            else{
                Optional<Teacher> teacher=teacherDao.findById(body.getTeacherId());
                if(teacher.isPresent()){
                    Student studentToUpdate=student.get();
                    List<Teacher> teachersTemp=studentToUpdate.getFollowedTeachers();
                        teachersTemp.add(teacher.get());
                        studentToUpdate.setFollowedTeachers(teachersTemp);
                        studentDao.save(studentToUpdate);
                        response.setIsSuccessful(true);
                        response.setMessage("Teacher Followed");
                        response.setData(true);
                        studentToUpdate.getFollowedTeachers().get(0).getName();
                        httpStatus = HttpStatus.OK;

                }
                else{
                    response.setIsSuccessful(false);
                    response.setMessage("Teacher does not Exists!");
                    response.setData(null);
                    httpStatus=HttpStatus.NOT_FOUND;
                }
            }
        }
        catch (DataIntegrityViolationException e){
            response.setIsSuccessful(false);
            response.setMessage("Teacher Already Followed");
            response.setData(null);
            httpStatus = HttpStatus.CONFLICT;
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
    public ResponseEntity<Response> getFollowing(Integer studentId) {
        Optional<Student> student=null;
        Response response=new Response();
        HttpStatus httpStatus=null;
        try{
            student= studentDao.findById(studentId);
            if(student.isPresent()) {
                response.setIsSuccessful(true);
                response.setMessage("Successful!");
                response.setData(student.get().getFollowedTeachers());
                httpStatus = HttpStatus.OK;
            }
            else{
                response.setIsSuccessful(false);
                response.setMessage("Student does not Exists!");
                response.setData(null);
                httpStatus=HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @Override
    public ResponseEntity<Response> getCategories(Integer studentId) {
        Optional<Student> student=null;
        Response response=new Response();
        HttpStatus httpStatus=null;
        try{
            student= studentDao.findById(studentId);
            if(student.isPresent()) {
                response.setIsSuccessful(true);
                response.setMessage("Successful!");
                response.setData(student.get().getCategories());
                httpStatus = HttpStatus.OK;
            }
            else{
                response.setIsSuccessful(false);
                response.setMessage("Student does not Exists!");
                response.setData(null);
                httpStatus=HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            e.printStackTrace();
            response.setIsSuccessful(false);
            response.setMessage("Server Error!");
            response.setData(false);
            httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

}
