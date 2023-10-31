package com.example.lms.mylecture.domain.entity;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.professor.entity.Professor;
import com.example.lms.student.domain.entity.Student;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Lecture lecture;

    @ManyToOne
    private Student student;

    @OneToOne
    private Professor professor;

}
