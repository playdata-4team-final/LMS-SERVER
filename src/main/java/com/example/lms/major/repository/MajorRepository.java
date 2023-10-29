package com.example.lms.major.repository;

import com.example.lms.major.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorRepository
        extends JpaRepository<Major, Long> {


    @Modifying
    @Query("delete FROM Major m where m.professor.id = :professorId and m.majorName = :majorName")
    public void deleteByProfessorIdAndMajorName(@Param("professorId") String professorId, @Param("majorName") String majorName);

    @Query("select m from Major as m where m.id = :id")
    public Major findByMajorId(@Param("id")Long id);


    @Query("select m from Major as m where m.professor.id = :id")
    public List<Major> findAllMajorById(@Param("id")String id);

    @Query("select m from Major as m where m.professor.id = :id and m.status = com.example.lms.lecture.domain.entity.Status.HOLDING")
    public List<Major> findApprovedMajorById(@Param("id")String id);

    @Query("UPDATE from Major as m SET m.status = com.example.lms.lecture.domain.entity.Status.ACCEPT where m.id = :id and m.status = com.example.lms.lecture.domain.entity.Status.HOLDING")
    public Major updateAcceptMajor(@Param("id")Long id);

    @Query("UPDATE from Major as m SET m.status = com.example.lms.lecture.domain.entity.Status.DENIED where m.id = :id and m.status = com.example.lms.lecture.domain.entity.Status.HOLDING")
    public Major updateDenyMajor(@Param("id")Long id);
}
