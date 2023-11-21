package com.example.lms.student.dto;

import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.ProfessorMajor;
import com.example.lms.student.entity.Student;
import com.example.lms.student.entity.StudentMajor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {
    private String id;
    private String studentName;
    private Integer year;
    private int studentNumber;
    private String email;
    private String phNumber;
    private List<Long> majorIds;

    public Student toEntity(){
        return Student.builder()
                .id(id)
                .studentName(studentName)
                .year(year)
                .studentNumber(studentNumber)
                .email(email)
                .phNumber(phNumber)
                .majorList(majorIds.stream()
                        .map(id -> StudentMajor.builder().major(Major.builder().id(id).build()).build())
                        .collect(Collectors.toList()))
                .build();
    }
}
