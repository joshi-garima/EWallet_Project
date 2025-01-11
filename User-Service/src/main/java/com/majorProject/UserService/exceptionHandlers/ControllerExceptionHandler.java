package com.majorProject.UserService.exceptionHandlers;

import com.majorProject.UserService.Exceptions.JasonProcessingException;
import com.majorProject.UserService.Exceptions.UserException;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    // any exception will be coming at that time how to handle, without interrupting the business logic

    @ExceptionHandler(value = JasonProcessingException.class)
    public ResponseEntity<Object> handle(JasonProcessingException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Object> handle(UserException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handle(SQLIntegrityConstraintViolationException e){
        return  new ResponseEntity<>("data already esist", HttpStatus.ALREADY_REPORTED);
    }

    @SuppressWarnings("null")
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException e){
        return  new ResponseEntity<>(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
