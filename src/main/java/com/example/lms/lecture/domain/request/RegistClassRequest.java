package com.example.lms.lecture.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistClassRequest {
    private Long lectureId;
    private String studentId;
    private Long propesserId;
}
