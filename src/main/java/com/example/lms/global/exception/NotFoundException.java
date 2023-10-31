package com.example.lms.global.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private String errorMsg;

    public NotFoundException(String s) {
        this.errorMsg = s;
    }

}
