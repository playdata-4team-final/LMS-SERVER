package com.team.lms.lecture.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class LectureFailException extends RuntimeException {
    private String responseCode;



    public LectureFailException(String s) {
        super(s);
        this.responseCode = s;
    }
}

