package com.nisi.jobportalbackend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Your service     → throws the ball (exception)
    //GlobalExceptionHandler → catches the ball and returns clean response
    //Without it       → ball hits the ground (ugly Spring
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
//RuntimeException        → you know what went wrong → 404 (not found)
//UsernameNotFoundException → you know what went wrong → 404 (user not found)
//
//Exception               → you have NO idea what went wrong
//                          something broke unexpectedly on server side
//                          → 500 Internal Server Error