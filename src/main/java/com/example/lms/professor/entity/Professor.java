package com.example.lms.professor.entity;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.dto.AllMajorDto;
import com.example.lms.major.entity.Major;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {

    @Id
    @Column(columnDefinition = "VARCHAR(36)", unique = true)
    private String id;

    private String professorName;
    @Column(unique = true)
    private String phNumber;
    @Column(unique = true)
    private String email;

    @OneToMany
    @JoinColumn(name = "professor_id")
    private List<Major> majorList;

    @OneToMany(mappedBy = "professor")
    private List<Lecture> lecture;


}