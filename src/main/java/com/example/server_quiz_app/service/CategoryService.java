package com.example.server_quiz_app.service;

import java.util.List;

import com.example.server_quiz_app.model.Category;
import com.example.server_quiz_app.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.server_quiz_app.dao.CategoryDao;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public ResponseEntity<Response> getCategories() {
		 List<Category> products=null;
	        Response res=new Response();
	        HttpStatus httpStatus=null;

	        try{
	            products= categoryDao.findAll();
	            res.setIsSuccessful(true);
	            res.setMessage("Successful!");
	            res.setData(products);
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

	public ResponseEntity<Response> getCategoriesOfStudent(int id) {
		List<Category> categories=null;
		HttpStatus httpStatus=null;

		Response response = new Response();

		try{
			categories=categoryDao.getCategoriesOfStudent(id);
			response.setIsSuccessful(true);
			response.setMessage("Successful!");
			response.setData(categories);
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
	
	

}
