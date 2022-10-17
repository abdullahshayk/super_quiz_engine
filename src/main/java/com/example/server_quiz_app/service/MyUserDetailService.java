package com.example.server_quiz_app.service;

import com.example.server_quiz_app.dao.StudentDao;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.security.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student=studentDao.findStudentByUsername(username);
        if(student==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetail(student);
    }
}
