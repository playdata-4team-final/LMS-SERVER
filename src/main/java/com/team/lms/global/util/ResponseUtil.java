package com.team.lms.global.util;

import com.team.lms.global.domain.response.LmsResponse;
import com.team.lms.global.domain.entity.ErrorEntity;

public class ResponseUtil {
    public static <T> LmsResponse<T> success(T response) {
        return new LmsResponse<> (true, response, null);
    }
    public static LmsResponse<?> error(ErrorEntity e) {
        return new LmsResponse<> (false, null, e);
    }
}