package com.example.lms.lecture.dto;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Semester;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class AllLectureDto {

    private Long id;
    private String lectureName;
    private Status status;
    private Integer maximumNumber;
    private Integer score;
    private String lectureComment;
    private LocalDateTime lectureDate;
    private Semester semester;
    private String majorName;
    private String professorName;


    public AllLectureDto(Long id, String lectureName, Status status, Integer maximumNumber, Integer score, String lectureComment, LocalDateTime lectureDate, Semester semester, String majorName, String professorName) {
        this.id = id;
        this.lectureName = lectureName;
        this.status = status;
        this.maximumNumber = maximumNumber;
        this.score = score;
        this.lectureComment = lectureComment;
        this.lectureDate = lectureDate;
        this.semester = semester;
        this.majorName = majorName;
        this.professorName = professorName;
    }

    public Lecture toEntity(){

        return Lecture
                .builder()
                .id(id)
                .lectureName(lectureName)
                .status(status)
                .maximumNumber(maximumNumber)
                .score(score)
                .lectureComment(lectureComment)
                .lectureDate(lectureDate)
                .semester(semester)
                .major(Major.builder().majorName(majorName).build())
                .professor(Professor.builder().professorName(professorName).build())
                .build();

    }

}
