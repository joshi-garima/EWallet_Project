package com.majorProject.NotificationService.exceptionHandlers;

import com.majorProject.NotificationService.Exception.JasonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    // any exception will be coming at that time how to handle, without interrupting the business logic

    @ExceptionHandler(value = JasonProcessingException.class)
    public ResponseEntity<Object> handle(JasonProcessingException e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
