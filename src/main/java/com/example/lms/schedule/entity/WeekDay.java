package com.example.lms.schedule.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeekDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int time;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    @Column(nullable = false)
    private Long monday;

    @Column(nullable = false)
    private Long tuesday;

    @Column(nullable = false)
    private Long wednesday;

    @Column(nullable = false)
    private Long thursday;

    @Column(nullable = false)
    private Long friday;


}

