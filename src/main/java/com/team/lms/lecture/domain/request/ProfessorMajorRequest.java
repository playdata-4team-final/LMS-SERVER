package com.team.lms.lecture.domain.request;
import com.team.lms.lecture.domain.entity.Status;
import com.team.lms.major.entity.Major;
import com.team.lms.professor.entity.Professor;
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

    private UUID professorId;
    private List<String> majorNames;
    private Boolean checkMajor;

    public List<Major> toEntity() {
        List<Major> majors = new ArrayList<>();

        for (String majorName : majorNames) {
            Major major = Major
                    .builder()
                    .professor(Professor.builder().id(professorId).build())
                    .majorName(majorName)
                    .checkMajor(checkMajor)
                    .status(Status.HOLDING)
                    .build();
            majors.add(major);
        }

        return majors;
    }

}
