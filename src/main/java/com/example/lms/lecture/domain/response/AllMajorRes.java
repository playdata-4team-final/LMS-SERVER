package com.example.lms.lecture.domain.response;

import com.example.lms.lecture.dto.AllMajorDto;
import com.example.lms.major.entity.Major;
import com.example.lms.lecture.domain.entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AllMajorRes {

    private Long id;
    private Boolean checkMajor;
    private String majorName;
    private Status status;

    public AllMajorRes(AllMajorDto major) {
        this.id = major.getId();
        this.checkMajor = major.getCheckMajor();
        this.majorName = major.getMajorName();
        this.status = major.getStatus();
    }
}
