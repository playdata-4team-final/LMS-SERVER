package com.team.lms.global.exception;

import lombok.Getter;

@Getter
public class InternalServerException extends RuntimeException {

    private String responseCode;

    public InternalServerException(String s) {
        super(s);
        this.responseCode = s;
    }
}
