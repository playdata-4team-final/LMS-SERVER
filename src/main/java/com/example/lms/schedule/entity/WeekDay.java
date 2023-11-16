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
    private Schedule schedule;

    @Column
    private Long monday;

    @Column
    private Long tuesday;

    @Column
    private Long wednesday;

    @Column
    private Long thursday;

    @Column
    private Long friday;


}

