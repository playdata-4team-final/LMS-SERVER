package com.team.lms.lecture.exception;

import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException{
    private String responseCode;

    public DuplicateException(String s) {
        this.responseCode = s;
    }

}
