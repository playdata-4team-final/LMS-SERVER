package com.team.lms.major.repository;

import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.major.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MajorRepository
        extends JpaRepository<Major, Long> {


    @Query("select m FROM Major AS m where m.professor.id = :professorId and m.majorName = :majorName")
    public void deleteByMajorNameAndProfessorId(@Param("professorId")UUID professorId, @Param("majorName")String majorName);

    @Query("select m from Major as m where m.professor.id = :id")
    public List<Major> findAllMajorById(@Param("id") UUID id);
}
