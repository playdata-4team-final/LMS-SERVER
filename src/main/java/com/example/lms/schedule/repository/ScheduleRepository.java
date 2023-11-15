package com.example.lms.schedule.repository;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Semester;
import com.example.lms.schedule.entity.Schedule;
import com.example.lms.schedule.entity.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ScheduleRepository
        extends JpaRepository<Schedule,Long> {

    @Query("SELECT Lecture FROM Schedule s join WeekDay w on w.schedule.id = s.id where s.memberId = :memberId and s.year = :year and s.semester = :semester")
    Optional<Schedule> findLectureBySchedule(@Param("memberId")String memberId, @Param("year")int year, @Param("semester")Semester semester);

}
