package com.example.lms.grade.dto;

import com.example.lms.grade.entity.Credit;
import com.example.lms.grade.entity.Grade;
import lombok.Getter;

@Getter
public class StudentGradeResponse {
    private Long id;

    private String lectureName;

    private String professorName;

    private Double score;

    private Credit credit;

    private Double totalScore;

    public StudentGradeResponse(Grade grade, Double totalScore) {
        this.id = grade.getId();
        this.lectureName = grade.getLecture().getLectureName();
        this.professorName = grade.getLecture().getProfessor().getProfessorName();
        this.score = grade.getScore();
        this.credit = grade.getCredit();
        this.totalScore = totalScore;
    }

}
