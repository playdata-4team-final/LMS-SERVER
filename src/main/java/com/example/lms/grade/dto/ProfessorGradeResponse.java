package com.example.lms.grade.dto;

import com.example.lms.grade.entity.Credit;
import com.example.lms.grade.entity.Grade;
import lombok.Getter;

@Getter
public class ProfessorGradeResponse {
    private Long id;

    private String lectureName;

    private String studentId;

    private Double score;

    private Credit credit;

    public ProfessorGradeResponse(Grade grade) {
        this.id = grade.getId();
        this.lectureName = grade.getLecture().getLectureName();
        this.studentId = grade.getStudent().getId();
        this.score = grade.getScore();
        this.credit = grade.getCredit();
    }
}
