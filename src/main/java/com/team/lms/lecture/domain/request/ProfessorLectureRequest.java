package com.team.lms.lecture.domain.request;

import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.lecture.domain.entity.Status;
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


    public Lecture toEntity(){
        return Lecture
                .builder()
                .professor(Professor.builder().id(professorId).build())
                .status(Status.HOLDING)
                .build();
    }

}
