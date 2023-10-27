package com.team.lms.lecture.domain.request;

import com.team.lms.lecture.domain.entity.Lecture;
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
public class ProfessorMajorRequest {

    private UUID professorId;
    private String majorName;
    private Boolean checkMajor;

    public Major toEntity(){
        return Major
                .builder()
                .professor(Professor.builder().id(professorId).build())
                .majorName(majorName)
                .checkMajor(checkMajor)
                .status(Status.HOLDING)
                .build();
    }

}
