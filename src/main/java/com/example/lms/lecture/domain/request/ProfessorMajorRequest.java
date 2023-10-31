package com.example.lms.lecture.domain.request;
import com.example.lms.major.entity.Major;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorMajorRequest {

    private String professorId;
    private String majorName;
    private Boolean checkMajor;

    public Major toEntity() {
            Major major = Major
                    .builder()
                    .professor(Professor.builder().id(professorId).build())
                    .majorName(majorName)
                    .checkMajor(checkMajor)
                    .status(Status.HOLDING)
                    .build();

        return major;
    }

}
