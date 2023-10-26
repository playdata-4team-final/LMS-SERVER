package com.example.lms.student.entity;

import com.example.lms.major.entity.Major;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    private UUID id;

    private String studentName;

    private Integer year;
    //학년

    private Integer studentNumber;
    private String email;
    private String phNumber;

    @OneToOne
    private Major major;
}

