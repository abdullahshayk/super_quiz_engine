package com.example.server_quiz_app.dao;

import com.example.server_quiz_app.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionDao extends JpaRepository<Option,Integer> {
}
