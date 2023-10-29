package com.example.lms.lecture.domain.entity;

import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.Professor;
import com.example.lms.room.entity.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String lectureName;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private Integer maximumNumber;
    @Column(nullable = false)
    private Integer score;
    private String lectureComment;
    @Column(nullable = false)
    private LocalDateTime lectureDate;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    @OneToOne
    private Room room;
    @OneToOne
    private Major major;
    @ManyToOne
    private Professor professor;

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", lectureName='" + lectureName + '\'' +
                ", status=" + status +
                ", maximumNumber=" + maximumNumber +
                ", score=" + score +
                ", lectureComment='" + lectureComment + '\'' +
                ", lectureDate=" + lectureDate +
                ", semester=" + semester +
                ", room=" + room +
                ", major=" + major +
                ", professor=" + professor +
                '}';
    }
}
