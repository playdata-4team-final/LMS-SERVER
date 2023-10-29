package com.example.lms.lecture.domain.response;

import com.example.lms.major.entity.Major;
import com.example.lms.lecture.domain.entity.Status;
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
    private String professorId;

    public AllMajorRes(Major major) {
        this.id = major.getId();
        this.checkMajor = major.getCheckMajor();
        this.majorName = major.getMajorName();
        this.status = major.getStatus();
        this.professorId = major.getProfessor().getId();
    }


}
