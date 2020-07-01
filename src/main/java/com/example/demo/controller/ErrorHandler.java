package com.example.demo.controller;

import com.example.demo.exception.ActivityManagementException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<String> processValidationError(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                 .getFieldErrors()
                 .stream()
                 .map(DefaultMessageSourceResolvable::getDefaultMessage)
                 .collect(Collectors.toList());
    }

    @ExceptionHandler(ActivityManagementException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String processActivityManagementError(ActivityManagementException ex) {
        return ex.getLocalizedMessage();
    }
}    
