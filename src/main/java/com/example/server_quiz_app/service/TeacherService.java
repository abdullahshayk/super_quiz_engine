package com.example.server_quiz_app.service;

import java.util.List;

import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.server_quiz_app.dao.TeacherDao;

@Service
public class TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	
	public ResponseEntity<Response> getTeachers() {
		 List<Teacher> teachers=null;
	        Response res=new Response();
	        HttpStatus httpStatus=null;
	        try{
	            teachers= teacherDao.findAll();
	            res.setIsSuccessful(true);
	            res.setMessage("Successful!");
	            res.setData(teachers);
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

	public ResponseEntity<Response> postTeacher(Teacher teacher) {
		Response res=new Response();
		HttpStatus httpStatus=null;
		try{
			teacherDao.save(teacher);
			res.setIsSuccessful(true);
			res.setMessage("Successful!");
			res.setData(true);
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
