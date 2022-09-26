package com.example.server_quiz_app.service.question_service;

import java.util.List;

import com.example.server_quiz_app.request_models.GetQuestionByCategoryAndTypeRequest;
import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.server_quiz_app.dao.QuestionDao;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionDao questionDao;

	private Response response=new Response();
	private HttpStatus httpStatus;
	@Override
	public ResponseEntity<Response> getQuestions() {
		 List<Question> question=null;
	        try{
	            question= questionDao.findAll();
				response.setIsSuccessful(true);
				response.setMessage("Successful! response="+question.size());
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
	@Override
	public ResponseEntity<Response> postQuestion(Question question) {
		try{
			questionDao.save(question);
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
	@Override
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
//	question_type:
//			1->Mcques-single
//			2->Mcques-multi
//			3->fill in the

	@Override
	public ResponseEntity<Response> getQuestionsByCategoryAndType(GetQuestionByCategoryAndTypeRequest request, Integer offset, Integer pageSize) {
		List<Integer> categoryId=request.getCategorys();
		List<Integer> questionType=request.getTypes();
		if((!inRange(categoryId,1,4))){
			response.setIsSuccessful(false);
			response.setMessage("Invalid category!");
			response.setData(false);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		else if((!inRange(questionType,1,3))){
			response.setIsSuccessful(false);
			response.setMessage("Invalid question type!");
			response.setData(false);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		else {
			try {
				List<Question> question = null;
				question = questionDao.findQuestionsByCategory(categoryId,questionType, PageRequest.of(offset,pageSize));
				response.setIsSuccessful(true);
				response.setMessage("Successful!");

				response.setData(question);
				httpStatus = HttpStatus.OK;
			} catch (Exception e) {
				e.printStackTrace();
				response.setIsSuccessful(false);
				response.setMessage("Server Error!");
				response.setData(false);
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		return ResponseEntity.status(httpStatus).body(response);
	}

	private static boolean inRange(List<Integer> list, int min, int max) {
		return list.stream().allMatch(i -> i >= min && i <= max);
	}

}

