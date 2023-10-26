package com.team.lms.mylecture.entity;

import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.professor.entity.Professor;
import com.team.lms.student.entity.Student;
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
public class MyLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Lecture lecture;

    @ManyToOne
    private Student student;

    @OneToOne
    private Professor professor;

}
