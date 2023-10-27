package com.team.lms.example.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {

    private String responseCode;

    public ClientException(String s) {
        super(s);
        this.responseCode = s;
    }
}
