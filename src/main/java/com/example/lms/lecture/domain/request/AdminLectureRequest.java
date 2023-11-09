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

    private Long lectureId;
    private Long roomId;
    private Long scheduleId;
    private WeekDay weekDay;
    private Integer startTime;
    private Integer classPeriod;
    private Boolean roomCheck;

    public Lecture toEntity(){
        return Lecture
                .builder()
                .id(lectureId)
                .room(Room
                        .builder()
                        .id(roomId)
                        .schedule(Schedule
                                .builder()
                                .id(scheduleId)
                                .weekDay(weekDay)
                                .classPeriod(classPeriod)
                                .lecture(Lecture
                                        .builder()
                                        .id(lectureId)
                                        .build())
                                .room(Room
                                        .builder()
                                        .id(roomId)
                                        .build())
                                .startTime(startTime)
                                .build())
                        .roomCheck(roomCheck)
                        .build())
                .build();
    }

}
