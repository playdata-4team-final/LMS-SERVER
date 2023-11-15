package com.example.lms.mylecture.domain.request;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Semester;
import com.example.lms.lecture.dto.AllLectureDto;
import com.example.lms.lecture.repository.LectureRepository;
import com.example.lms.room.entity.Room;
import com.example.lms.room.repository.RoomRepository;
import com.example.lms.schedule.entity.Schedule;
import com.example.lms.schedule.entity.WeekDay;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyScheduleRequest {

    private int time;
    private int year;
    private Semester semester;
    private long lectureId1;
    private long lectureId2;
    private long lectureId3;
    private long lectureId4;
    private long lectureId5;

    public Schedule toEntities() {
        WeekDay weekDay = WeekDay
                .builder()
                .time(time)
                .monday(lectureId1)
                .tuesday(lectureId2)
                .wednesday(lectureId3)
                .thursday(lectureId4)
                .friday(lectureId5)
                .build();

        return Schedule.builder()
                .year(year)
                .semester(semester)
                .weekdays(Collections.singletonList(weekDay))
                .build();
    }
}
