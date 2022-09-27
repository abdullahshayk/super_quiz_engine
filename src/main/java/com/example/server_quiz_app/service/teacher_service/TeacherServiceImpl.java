package com.example.server_quiz_app.service.teacher_service;

import java.util.List;
import java.util.Optional;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Response;
import com.example.server_quiz_app.model.Student;
import com.example.server_quiz_app.request_models.UserCategory;
import com.example.server_quiz_app.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.server_quiz_app.dao.TeacherDao;

@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	private TeacherDao teacherDao;
	private HttpStatus httpStatus;


	@Override
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

	@Override
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

	@Override
	public ResponseEntity<Response> addCategories(Integer teacherId, List<Category> categories) {
		Response response = new Response();
		try{
			Optional<Teacher> teacher=teacherDao.findById(teacherId);
			if(!teacher.isPresent()){
				response.setIsSuccessful(false);
				response.setMessage("teacher not found!");
				response.setData(null);
				httpStatus=HttpStatus.NOT_FOUND;
			}
			else{
				Teacher teacherToUpdate=teacher.get();
				teacherToUpdate.setCategories(categories);
				teacherDao.save(teacherToUpdate);
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
	public ResponseEntity<Response> addCategories(UserCategory body) {
		Response response = new Response();
		try{
			Teacher teacher=teacherDao.findTeacherByName(body.getUsername());
			if(teacher==null){
				response.setIsSuccessful(false);
				response.setMessage("User with this username not found!");
				response.setData(null);
				httpStatus=HttpStatus.NOT_FOUND;
			}
			else{
				teacher.setCategories(body.getCategoryList());
				teacherDao.save(teacher);
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
	public ResponseEntity<Response> getCategories(Integer teacherId) {
		Optional<Teacher> teacher=null;
		Response response=new Response();
		HttpStatus httpStatus=null;
		try{
			teacher= teacherDao.findById(teacherId);
			if(teacher.isPresent()) {
				response.setIsSuccessful(true);
				response.setMessage("Successful!");
				response.setData(teacher.get().getCategories());
				httpStatus = HttpStatus.OK;
			}
			else{
				response.setIsSuccessful(false);
				response.setMessage("Teacher does not Exists!");
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
		return ResponseEntity.status(httpStatus).body(response); 	}

}
