package com.example.lms.grade.entity;

import com.example.lms.lecture.domain.entity.Lecture;
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
public class Grade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Credit credit;

    private Double score;

    @ManyToOne
    private Lecture lecture;

    @ManyToOne
    private Student student;

    public void saveCredit(Credit credit) {
        this.credit = credit;
    }

    public void updateScore(Double score) {
        this.score = score;
    }
}
