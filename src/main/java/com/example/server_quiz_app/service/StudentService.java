package com.example.server_quiz_app.service;

import com.example.server_quiz_app.dao.StudentDao;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.security.JwtUtil;
import com.example.server_quiz_app.utils.EncryptionAndDecryption;
import org.hibernate.PropertyValueException;
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

@Service
public class StudentService  {
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



    public ResponseEntity<Response> signUpStudent(Student student) {
        Response response = new Response();
      try{
         // student.setPassword(passwordEncoder.encode(student.getPassword()));
          studentDao.save(student);
          response.setIsSuccessful(true);
          response.setMessage("Successful!");
          response.setData(true);
          httpStatus=HttpStatus.OK;
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
}
