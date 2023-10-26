package com.team.lms.lecture.domain.request;

import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.lecture.domain.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLectureRequest {

    private UUID adminId;
    private Long lectureId;

    public Lecture toEntity(){
        return Lecture
                .builder()
                .status(Status.ACCEPT)
                .build();
    }

}
