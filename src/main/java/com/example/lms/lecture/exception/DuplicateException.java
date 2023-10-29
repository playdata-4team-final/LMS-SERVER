package com.example.lms.lecture.exception;

import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException{
    private String errorMsg;

    public DuplicateException(String s) {
        this.errorMsg = s;
    }

}
