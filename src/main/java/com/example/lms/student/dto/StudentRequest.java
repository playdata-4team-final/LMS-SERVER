package com.example.lms.student.dto;

import com.example.lms.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

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

    public Student toEntity(){
        return Student.builder()
                .id(id)
                .studentName(studentName)
                .year(year)
                .studentNumber(studentNumber)
                .email(email)
                .phNumber(phNumber)
                .build();
    }
}
