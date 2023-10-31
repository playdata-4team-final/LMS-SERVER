package com.example.lms.lecture.domain.request;

import com.example.lms.major.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminMajorRequest {

    private String adminId;
    private Long majorId;

    public Major toEntity() {
        return Major
                .builder()
                .id(majorId)
                .build();
    }
}
