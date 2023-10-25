package com.example.lms.lecture.entity;

import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.Professor;
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

    private String lectureName;
    private Status status;
    private Integer maximumNumber;
    private Integer score;
    private String lectureComment;
    private Date lectureDate;
    private Semester semester;

    @OneToOne
    private Major major;

    @ManyToOne
    @JoinColumn(name = "lecture")
    private Professor professor;
}
