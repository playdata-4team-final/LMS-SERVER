package com.example.lms.lecture.dto;

import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.Professor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllMajorDto {

    private Long id;
    private Boolean checkMajor;
    private String majorName;
    private Status status;
    private String professorId;

    //DTO 추가
    public AllMajorDto(Major major) {
        this.id = major.getId();
        this.checkMajor = major.getCheckMajor();
        this.majorName = major.getMajorName();
        this.status = major.getStatus();
        this.professorId = major.getProfessor().getId();
    }

    // 생성자 추가
    public AllMajorDto(Long id, Boolean checkMajor, String majorName, Status status, String someOtherField) {
        this.id = id;
        this.checkMajor = checkMajor;
        this.majorName = majorName;
        this.status = status;
    }

}
