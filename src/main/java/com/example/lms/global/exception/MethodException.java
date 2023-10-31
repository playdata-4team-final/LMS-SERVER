package com.example.lms.global.exception;

import lombok.Getter;

@Getter
public class MethodException extends RuntimeException{
    private String errorMsg;

    public MethodException(String s) {
        this.errorMsg = s;
    }

}
