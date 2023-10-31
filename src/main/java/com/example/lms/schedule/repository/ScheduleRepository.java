package com.example.lms.schedule.repository;

import com.example.lms.schedule.entity.Schedule;
import com.example.lms.schedule.entity.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository
        extends JpaRepository<Schedule,Long> {
}
