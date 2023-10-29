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
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLectureRequest {

    private String adminId;
    private Long lectureId;
    private Long roomNumber;
    private WeekDay weekDay;
    private Integer startTime;
    private Boolean roomCheck;


    public Lecture toEntity(){
        return Lecture
                .builder()
                .id(lectureId)
                .room(Room
                        .builder()
                        .roomNumber(roomNumber)
                        .schedule(Schedule
                                .builder()
                                .weekDay(weekDay)
                                .startTime(startTime)
                                .roomCheck(roomCheck)
                                .build())
                        .build())
                .build();
    }

}
