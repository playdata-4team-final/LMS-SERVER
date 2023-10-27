package com.team.lms.lecture.domain.request;

import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.lecture.domain.entity.Semester;
import com.team.lms.lecture.domain.entity.Status;
import com.team.lms.major.entity.Major;
import com.team.lms.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorLectureRequest {

    private UUID professorId;
    private Long majorId;
    private String lectureName;
    private Integer maximumNumber;
    private Integer score;
    private Semester semester;


    public Lecture toEntity(){
        return Lecture
                .builder()
                .professor(Professor.builder().id(professorId).build())
                .major(Major.builder().id(majorId).build())
                .lectureName(lectureName)
                .score(score)
                .semester(semester)
                .status(Status.HOLDING)
                .build();
    }

}
