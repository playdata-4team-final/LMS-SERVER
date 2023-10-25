package com.example.lms.professor.entity;

import com.example.lms.lecture.entity.Lecture;
import com.example.lms.major.entity.Major;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {

    @Id
    private UUID id;

    private String professorName;
    private String phNumber;
    private String email;

    @OneToOne
    private Major major;

    @OneToMany(mappedBy = "professor")
    private List<Lecture> lecture;
}
