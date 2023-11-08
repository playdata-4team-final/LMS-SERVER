package com.example.lms.grade.dto;


import com.example.lms.grade.entity.Grade;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.student.entity.Student;
import lombok.Getter;

@Getter
public class GradeRequest {
    private String studentId;
    private Double score;

    public Grade toEntity(Lecture lecture, Student student, Double score){
        return Grade.builder()
                .lecture(lecture)
                .student(student)
                .score(score)
                .build();

    }
}
