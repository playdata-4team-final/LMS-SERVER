package com.example.lms.lecture.domain.request;

import com.example.lms.major.entity.Major;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Semester;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorLectureRequest {

    private String professorId;
    private Long majorId;
    private String lectureName;
    private String lectureComment;
    private Integer maximumNumber;
    private Integer score;
    private Semester semester;


    public Lecture toEntity(){
        return Lecture
                .builder()
                .professor(Professor.builder().id(professorId).build())
                .major(Major.builder().id(majorId).build())
                .lectureName(lectureName)
                .lectureComment(lectureComment)
                .score(score)
                .semester(semester)
                .maximumNumber(maximumNumber)
                .status(Status.HOLDING)
                .lectureDate(LocalDateTime.now())
                .build();
    }

}
