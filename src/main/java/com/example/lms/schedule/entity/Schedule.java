package com.example.lms.schedule.entity;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.room.entity.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private WeekDay weekDay;
    @Column
    @Min(1) @Max(8)
    private Integer classPeriod;
    @Column
    @Min(1) @Max(5)
    private Integer startTime;
    private Boolean roomCheck; // 강의실 사용 여부

    @OneToOne
    private Lecture lecture;

    @OneToOne
    private Room room;

}
