package com.team.lms.lecture.domain.response;

import com.team.lms.lecture.domain.entity.Lecture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllLectureRes {

    private Long classId;
    private String className;
    private Boolean major;
    private int grade;
    private String day;
    private int time;
    private int lp;
    private String room;
    private String pfName;
    private String mName;
    private Boolean status;

    public AllLectureRes(Long classId, String className, Boolean major, int grade, String day, int time, int lp, String room, String pfName, String mName, Boolean status) {
        this.classId = classId;
        this.className = className;
        this.major = major;
        this.grade = grade;
        this.day = day;
        this.time = time;
        this.lp = lp;
        this.room = room;
        this.pfName = pfName;
        this.mName = mName;
        this.status = status;
    }

    public AllLectureRes(Lecture lecture) {
    }
}
