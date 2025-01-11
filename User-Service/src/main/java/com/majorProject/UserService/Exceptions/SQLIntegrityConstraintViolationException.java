package com.majorProject.UserService.Exceptions;

public class SQLIntegrityConstraintViolationException extends Exception {
    public SQLIntegrityConstraintViolationException(String msg){
        super(msg);
    }
}
