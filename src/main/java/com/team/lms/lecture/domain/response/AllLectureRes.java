package com.team.lms.lecture.domain.response;

import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.lecture.domain.entity.Semester;
import com.team.lms.lecture.domain.entity.Status;
import com.team.lms.major.entity.Major;
import com.team.lms.professor.entity.Professor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class AllLectureRes {

    private Long id;
    private String lectureName;
    private Status status;
    private Integer maximumNumber;
    private Integer score;
    private String lectureComment;
    private Date lectureDate;
    private Semester semester;
    private Long majorId;
    private UUID professorId;

    public AllLectureRes(Long id, String lectureName, Status status, Integer maximumNumber, Integer score, String lectureComment, Date lectureDate, Semester semester, Long majorId, UUID professorId) {
        this.id = id;
        this.lectureName = lectureName;
        this.status = status;
        this.maximumNumber = maximumNumber;
        this.score = score;
        this.lectureComment = lectureComment;
        this.lectureDate = lectureDate;
        this.semester = semester;
        this.majorId = majorId;
        this.professorId = professorId;
    }

    public AllLectureRes(Lecture lecture) {
    }
}
