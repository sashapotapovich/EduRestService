package com.example.demo.exception;

public class ActivityManagementException extends RuntimeException{
    public ActivityManagementException(String msg) {
        super(msg);
    }
    public ActivityManagementException(){
        super();
    }
}
