package com.example.lms.room.entity;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long roomNumber;

    @OneToOne
    private Schedule schedule;

    @Column(columnDefinition = "boolean default false")
    private Boolean roomCheck;

    @OneToOne
    private Lecture lecture;


    public void changeRoomCheck(Boolean roomCheck){this.roomCheck = roomCheck;}

    public void changeSchedule(Schedule schedule){this.schedule = schedule;}
    public void changeLecture(Lecture lecture){this.lecture=lecture;}

}
