package com.example.server_quiz_app.service;

import java.util.List;

import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.server_quiz_app.dao.QuestionDao;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;
	public ResponseEntity<Response> getQuestions() {
		 List<Question> question=null;
	        Response res=new Response();
	        HttpStatus httpStatus=null;

	        try{
	            question= questionDao.findAll();
	            res.setIsSuccessful(true);
	            res.setMessage("Successful!");
	            res.setData(question);
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

}
