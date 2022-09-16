package com.example.server_quiz_app.service;

import com.example.server_quiz_app.dao.OptionDao;
import com.example.server_quiz_app.model.Option;
import com.example.server_quiz_app.model.Question;
import com.example.server_quiz_app.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    @Autowired
    private OptionDao optionDao;

    public ResponseEntity<Response> getOptions() {
        List<Option> products=null;
        Response res=new Response();
        HttpStatus httpStatus=null;

        try{
            products= optionDao.findAll();
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

    public ResponseEntity<Response> saveOption(Option option) {
        Response response=new Response();
         HttpStatus httpStatus=null;

        try{
            optionDao.save(option);
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
}
