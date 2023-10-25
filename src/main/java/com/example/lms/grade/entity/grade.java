package com.example.lms.grade.entity;

import com.example.lms.lecture.entity.Lecture;
import com.example.lms.student.entity.Student;
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
public class grade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Credit credit;

    private Double score;

    @OneToOne
    private Lecture lecture;

    @ManyToOne
    private Student student;
}
