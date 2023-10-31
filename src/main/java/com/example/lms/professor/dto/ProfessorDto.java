package com.example.lms.professor.dto;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.Professor;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessorDto {

    private String id;

    public ProfessorDto(Professor professor){
        this.id = professor.getId();
    }
    public ProfessorDto(String id) {
        this.id = id;
    }
}
