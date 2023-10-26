package com.team.lms.global.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LmsRequest<T> {
        private String requestId;
        private T request;
        private String body;
}
