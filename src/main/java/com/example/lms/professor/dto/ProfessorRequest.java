package com.example.lms.professor.dto;

import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.Professor;
import com.example.lms.professor.entity.ProfessorMajor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorRequest {
    private String id;
    private String professorName;
    private String email;
    private String phNumber;
    private List<Long> majorIds;

    public Professor toEntity(){
        return Professor.builder()
                .id(id)
                .professorName(professorName)
                .email(email)
                .phNumber(phNumber)
                .majorList(
                        majorIds.stream()
                                .map(id -> ProfessorMajor.builder().major(Major.builder().id(id).build()).build())
                                .collect(Collectors.toList()))
                .build();
    }
}
