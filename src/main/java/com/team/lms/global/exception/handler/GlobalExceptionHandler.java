package com.team.lms.global.exception.handler;

import com.team.lms.global.exception.InternalServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<Object> handleInternalServerException(
            InternalServerException ex) {
        String responseBody = "Internal Server Error: " + ex.getMessage();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }


}