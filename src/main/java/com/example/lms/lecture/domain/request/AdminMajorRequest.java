package com.example.lms.lecture.domain.request;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.major.entity.Major;
import com.example.lms.room.entity.Room;
import com.example.lms.schedule.entity.Schedule;
import com.example.lms.schedule.entity.WeekDay;
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
    private Status status;

    public Major toEntity() {
        return Major
                .builder()
                .status(status)
                .build();
    }
}
