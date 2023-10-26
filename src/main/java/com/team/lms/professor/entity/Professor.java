package com.team.lms.professor.entity;

import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.major.entity.Major;
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
