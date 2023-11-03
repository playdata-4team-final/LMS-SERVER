package com.example.lms.student.entity;

import com.example.lms.major.entity.Major;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @Column(columnDefinition = "VARCHAR(36)", unique = true)
    private String id;
    private String studentName;
    //학년
    private Integer year;
    private Integer studentNumber;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phNumber;

    @OneToOne
    private Major major;
}

