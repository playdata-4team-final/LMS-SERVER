package com.team.lms.global.util;

import com.team.lms.global.domain.response.LmsResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class ResponseUtil {
    public static <T> LmsResponse<T> success(T response, HttpStatus status) {
        LocalDateTime currentTime = LocalDateTime.now();
        return new LmsResponse<> (status, response, null, currentTime);
    }
    public static <T> LmsResponse<T> error(T response, String e, HttpStatus status) {
        LocalDateTime currentTime = LocalDateTime.now();
        return new LmsResponse<> (status, response, e, currentTime);
    }

}