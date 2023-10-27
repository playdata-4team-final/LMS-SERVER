package com.team.lms.lecture.domain.entity;

import com.team.lms.major.entity.Major;
import com.team.lms.professor.entity.Professor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String lectureName;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private Integer maximumNumber;
    @Column(nullable = false)
    private Integer score;
    private String lectureComment;
    @Column(nullable = false)
    private Date lectureDate;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    @OneToOne
    private Major major;
    @ManyToOne
    private Professor professor;
}
