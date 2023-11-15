package com.example.lms.lecture.domain.request;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.room.entity.Room;
import com.example.lms.schedule.entity.Schedule;
import com.example.lms.schedule.entity.WeekDay;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLectureRequest {

    private Lecture lecture;
    private Boolean roomCheck;
    private WeekDay weekDay;
    private String roomNumber;

    public Lecture toEntity() {
        return Lecture.builder()
                .id(lecture.getId())
                .room(Room.builder()
                        .schedule(Schedule.builder()
                                .weekdays(Collections.singletonList(weekDay))
                                .lectures(Collections.singletonList(lecture))
                                .build())
                        .roomNumber(roomNumber)
                        .roomCheck(roomCheck)
                        .build())
                .build();
    }
}
