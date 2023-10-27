package com.team.lms.lecture.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private String responseCode;

    public NotFoundException(String s) {
        this.responseCode = s;
    }

}
