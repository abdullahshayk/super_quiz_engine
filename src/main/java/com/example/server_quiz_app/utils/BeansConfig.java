package com.example.server_quiz_app.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeansConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        int strength=10;
        return new BCryptPasswordEncoder(strength);
    }


}

