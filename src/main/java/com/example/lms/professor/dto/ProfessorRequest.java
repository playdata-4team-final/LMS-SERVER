package com.example.lms.professor.dto;

import com.example.lms.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorRequest {
    private String id;
    private String professorName;
    private String email;
    private String phNumber;

    public Professor toEntity(){
        return Professor.builder()
                .id(id)
                .professorName(professorName)
                .email(email)
                .phNumber(phNumber)
                .build();
    }
}
