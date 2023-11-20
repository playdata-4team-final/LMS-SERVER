package com.example.lms.lecture.dto;

import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.Professor;
import com.example.lms.professor.entity.ProfessorMajor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AllMajorDto {

    private Long id;
    private Boolean checkMajor;
    private String majorName;
    private Status status;
    private Professor professor;

    //DTO 추가
    public AllMajorDto(ProfessorMajor professorMajor) {
        this.id = professorMajor.getMajor().getId();
        this.checkMajor = professorMajor.getMajor().getCheckMajor();
        this.majorName = professorMajor.getMajor().getMajorName();
        this.status = professorMajor.getMajor().getStatus();
        this.professor = professorMajor.getProfessor();
    }

    // 생성자 추가
    public AllMajorDto(Long id, Boolean checkMajor, String majorName, Status status) {
        this.id = id;
        this.checkMajor = checkMajor;
        this.majorName = majorName;
        this.status = status;
    }

}
