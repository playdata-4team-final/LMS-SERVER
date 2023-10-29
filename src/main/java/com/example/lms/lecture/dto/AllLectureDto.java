package com.example.lms.lecture.dto;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Semester;
import com.example.lms.lecture.domain.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
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


    public AllLectureDto(Lecture lecture) {

        this.id = lecture.getId();
        this.lectureName = lecture.getLectureName();
        this.status = lecture.getStatus();
        this.maximumNumber = lecture.getMaximumNumber();
        this.score = lecture.getScore();
        this.lectureComment = lecture.getLectureComment();
        this.lectureDate = lecture.getLectureDate();
        this.semester = lecture.getSemester();
        this.majorName = lecture.getMajor().getMajorName();
        this.professorName = lecture.getProfessor().getProfessorName();
    }

}
