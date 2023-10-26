package com.team.lms.global.domain.response;

import com.team.lms.global.domain.entity.ErrorEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LmsResponse<T> {
    private boolean success;
    private T response;
    private ErrorEntity error;

}
