package com.team.lms.global.domain.response;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LmsResponse<T> {
    private HttpStatus code;
    private T data;
    private String msg;
    private LocalDateTime currentTime;
}
