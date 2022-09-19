package com.example.server_quiz_app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

public class Response {
    private Boolean isSuccessful;
    private String message;
    private Object data;

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }
    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
