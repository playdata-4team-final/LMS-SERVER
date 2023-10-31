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
    @Enumerated(EnumType.STRING)
    private WeekDay weekDay;
    @Column
    @Min(1) @Max(8)
    private Integer classPeriod;
    @Column
    @Min(1) @Max(5)
    private Integer startTime;
    @OneToOne
    private Room room;

    public void setClassPeriod(Integer classPeriod) {
        this.classPeriod = classPeriod;
    }
    public void changeWeekDay(WeekDay weekDay){
        this.weekDay = weekDay;
    }
    public void changeStartTime(Integer startTime){this.startTime = startTime;}
    public void changeClassPeriod(Integer classPeriod){this.classPeriod = classPeriod;}
    public void changeRoom(Room room){this.room = room;}
}
