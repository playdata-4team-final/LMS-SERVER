package com.team.lms.global.controller.handler;

import com.team.lms.global.domain.entity.ErrorEntity;
import com.team.lms.global.util.ResponseUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // body는 현재 응답 바디를 나타냅니다.

        if(body instanceof ErrorEntity) {
            return ResponseUtil.error((ErrorEntity) body);
        }

        // 예시: 응답 바디가 문자열이라고 가정하고, "Modified: "를 추가하여 반환
        if (body instanceof String) {
            String modifiedBody = "Modified: " + body;
            return modifiedBody;
        }

        // 예시: 응답 바디가 List이라고 가정하고, 각 요소에 추가 작업을 수행하여 반환
        if (body instanceof List<?>) {
            List<String> modifiedList = new ArrayList<>();
            for (Object item : (List<?>) body) {
                modifiedList.add("Modified: " + item.toString());
            }
            return modifiedList;
        }

        // 기본적으로는 원본 응답 바디를 그대로 반환합니다.
        return ResponseUtil.success(body);
    }



}

