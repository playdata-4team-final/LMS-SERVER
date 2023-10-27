package com.team.lms.student.entity;

import com.team.lms.major.entity.Major;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String studentName;

    private Integer year;

    private Integer studentNumber;
    private String email;
    private String phNumber;

    @OneToOne
    private Major major;
}

