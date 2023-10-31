package com.example.lms.global.exception.handler;

import com.example.notice.global.exception.ClientException;
import com.example.notice.global.exception.DuplicateException;
import com.example.notice.global.exception.MethodException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientException.class)
    protected ResponseEntity<Object> handleClientException(
            ClientException ex) {
        String responseBody = "Client Exception: " + ex.getMessage();
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    protected ResponseEntity<Object> handleNotFondException(
            ChangeSetPersister.NotFoundException ex) {
        String responseBody = "NotFound Exception: " + ex.getMessage();
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<Object> handleDuplicateException(
            DuplicateException ex) {
        String responseBody = "Duplicate Exception: " + ex.getMessage();
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodException.class)
    protected ResponseEntity<Object> handleMethodException(
            MethodException ex) {
        String responseBody = "Method Exception: " + ex.getMessage();
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }


}