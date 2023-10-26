package com.team.lms.global.exception.handler;

import com.team.lms.global.domain.entity.ErrorEntity;
import com.team.lms.global.exception.FailedLoginException;
import com.team.lms.lecture.exception.LectureFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    //@ExceptionHandler(value = {NoUserExistException.class, WrongPasswordException.class})
    @ExceptionHandler(FailedLoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handleLoginException(FailedLoginException e) {

        log.error("Login Exception({}) - {}", e.getClass().getSimpleName(), e.getMessage());
        return new ErrorEntity(e.getResponseCode());
    }

    @ExceptionHandler(LectureFailException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorEntity handleClassFailException(LectureFailException e) {
        log.error("LectureFail Exception({}) - {}", e.getClass().getSimpleName(), e.getMessage());
        return new ErrorEntity(e.getResponseCode());
    }
}