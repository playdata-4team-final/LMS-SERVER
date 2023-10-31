package com.example.lms.global.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {

    private String errorMsg;

    public ClientException(String s) {
        super(s);
        this.errorMsg = s;
    }

}
