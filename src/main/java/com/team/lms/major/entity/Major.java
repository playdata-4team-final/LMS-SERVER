package com.team.lms.major.entity;

import com.team.lms.lecture.domain.entity.Status;
import com.team.lms.professor.entity.Professor;
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
public class Major {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean checkMajor;
    private String majorName;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private Professor professor;

}
