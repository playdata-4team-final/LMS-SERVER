package com.example.lms.global.util;

import com.example.notice.global.domain.response.LmsResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class ResponseUtil {
    public static <T> LmsResponse<T> success(T response, HttpStatus status, String msg, Exception e) {
        LocalDateTime currentTime = LocalDateTime.now();
        return new LmsResponse<>(status, response, msg, e.getMessage(), currentTime);
    }

}