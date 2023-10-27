package com.team.lms.lecture.domain.response;

import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.lecture.domain.entity.Status;
import com.team.lms.major.entity.Major;
import com.team.lms.professor.entity.Professor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AllMajorRes {

    private Long id;
    private Boolean checkMajor;
    private String majorName;
    private Status status;
    private UUID professorId;

    public AllMajorRes(Long id, Boolean checkMajor, String majorName, Status status, UUID professorId) {
        this.id = id;
        this.checkMajor = checkMajor;
        this.majorName = majorName;
        this.status = status;
        this.professorId = professorId;
    }

    public AllMajorRes(Major major) {
    }
}
