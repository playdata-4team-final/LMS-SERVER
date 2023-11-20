package com.example.lms.lecture.domain.request;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.major.entity.Major;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.major.repository.MajorRepository;
import com.example.lms.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorMajorRequest {

    private String professorId;
    private String majorName;
    private Boolean checkMajor;
    private MajorRepository majorRepository;

    public Major toEntity() {
        Major major = Major
                .builder()
                .majorName(majorName)
                .checkMajor(checkMajor)
                .status(Status.HOLDING)
                .build();

        return major;
    }
}
