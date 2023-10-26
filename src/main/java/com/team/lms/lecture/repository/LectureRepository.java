package com.team.lms.lecture.repository;

import com.team.lms.lecture.domain.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LectureRepository
        extends JpaRepository<Lecture, Long> {

    @Query("select l from Lecture as l where l.professor.id = :id")
    public List<Lecture> findAllLectureById(@Param("id") UUID id);

    @Query("SELECT l FROM Lecture as l")
    public List<Lecture> findAllList();

    @Modifying
    @Query("UPDATE Lecture as l SET l.status = ACCEPT WHERE l.id = :lectureId")
    public void updateLecture(@Param("lectureId") UUID lectureId);

}
