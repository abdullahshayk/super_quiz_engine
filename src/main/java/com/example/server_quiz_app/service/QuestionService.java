package com.example.server_quiz_app.service;

import java.util.List;

import com.example.server_quiz_app.dao.OptionDao;
import com.example.server_quiz_app.model.Option;
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
	@Autowired
	private OptionDao optionDao;

	private Response response=new Response();
	private HttpStatus httpStatus;
	public ResponseEntity<Response> getQuestions() {
		 List<Question> question=null;
	        try{
	            question= questionDao.findAll();
				response.setIsSuccessful(true);
				response.setMessage("Successful!");
				response.setData(question);
	            httpStatus=HttpStatus.OK;
	        }catch (Exception e){
	            e.printStackTrace();
				response.setIsSuccessful(false);
				response.setMessage("Server Error!");
				response.setData(false);
	            httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return ResponseEntity.status(httpStatus).body(response);
	
	}

	public ResponseEntity<Response> postQuestion(Question question) {
		try{
			questionDao.save(question);
//			question.getOptions().forEach(option -> {
//				Option opt=new Option();
//				opt.setOption(option.getOption());
//				opt.setQuestion(question);
//				opt.setIsCorrect((byte) 0);
//				optionDao.save(opt);
//			});
			response.setIsSuccessful(true);
			response.setMessage("Successful!");
			response.setData(true);
			httpStatus=HttpStatus.OK;
		}catch (Exception e){
			e.printStackTrace();
			response.setIsSuccessful(false);
			response.setMessage("Server Error!");
			response.setData(false);
			httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return ResponseEntity.status(httpStatus).body(response);
	}

	public ResponseEntity<Response> getQuestionOfSpecificTeacher(Integer teacherId) {
		try{
			List<Question> question = null;
			if(questionDao.doesTeacherExist(teacherId)!=null) {
				question = questionDao.findQuestionsByTeacherId(teacherId);
				response.setMessage("Successful!");
			}
			else {
				response.setMessage("Teacher does not exist");
			}
			response.setIsSuccessful(true);
			response.setData(question);
			httpStatus = HttpStatus.OK;
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
