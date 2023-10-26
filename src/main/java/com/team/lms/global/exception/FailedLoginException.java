package com.team.lms.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class FailedLoginException extends RuntimeException {
    private String responseCode;


    public FailedLoginException(String message) {
        super(message);
        this.responseCode = message;
    }

    public FailedLoginException(String message, Throwable cause) {
        super(message, cause);
        this.responseCode = message;
    }

    public FailedLoginException(Throwable cause) {
        super(cause);
    }

    public FailedLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.responseCode = message;
    }
}
