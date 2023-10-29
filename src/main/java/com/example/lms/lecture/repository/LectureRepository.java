package com.example.lms.lecture.repository;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.schedule.entity.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LectureRepository
        extends JpaRepository<Lecture, Long> {

    @Query("select l from Lecture as l where l.professor.id = :id")
    public List<Lecture> findAllLectureById(@Param("id") String id);

    @Query("SELECT l FROM Lecture as l")
    public List<Lecture> findAllList();

    @Modifying
    @Query("UPDATE Lecture as l SET l.status = com.example.lms.lecture.domain.entity.Status.ACCEPT, l.room.roomNumber = :roomNumber, l.room.schedule.roomCheck = true, l.room.schedule.weekDay = :weekDay, l.room.schedule.startTime = :startTime, l.room.schedule.roomCheck = :roomCheck WHERE l.id = :lectureId")
    public void updateLecture(@Param("lectureId") Long lectureId, @Param("roomNumber") Long roomNumber, @Param("weekDay")WeekDay weekDay, @Param("startTime")Integer StartTime, @Param("roomCheck")Boolean roomCheck);


}
