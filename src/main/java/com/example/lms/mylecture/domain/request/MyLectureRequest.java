package com.example.lms.mylecture.domain.request;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.mylecture.domain.entity.MyLecture;
import com.example.lms.professor.entity.Professor;
import com.example.lms.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyLectureRequest {
    private String studentId;
    private String professorId;
    private Long lectureId;

    public MyLecture toEntity(){
        return MyLecture
                .builder()
                .lecture(Lecture.builder().id(lectureId).build())
                .student(Student.builder().id(studentId).build())
                .professor(Professor.builder().id(professorId).build())
                .build();
    }
}
