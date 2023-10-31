package com.example.lms.major.repository;
import com.example.lms.lecture.dto.AllMajorDto;
import com.example.lms.major.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MajorRepository
        extends JpaRepository<Major, Long> {

    @Modifying
    @Query("delete FROM Major m where m.id = :majorId")
    void deleteByMajorId(@Param("majorId") Long majorId);
    @Query("SELECT new com.example.lms.lecture.dto.AllMajorDto(m.id, m.checkMajor, m.majorName, m.status, m.professor.id) from Major as m where m.status = com.example.lms.lecture.domain.entity.Status.HOLDING") //교수 정보도 보여줘야하니까
    List<AllMajorDto> findAllByStatus();

    @Query("select m FROM Major m where m.professor.id = :professorId and m.majorName = :majorName")
    AllMajorDto findByProfessorIdAndMajorName(@Param("professorId") String professorId, @Param("majorName") String majorName);

    @Query("select m from Major as m where m.id = :id and m.status = com.example.lms.lecture.domain.entity.Status.HOLDING")
    AllMajorDto findByMajorIdandStatus(@Param("id")Long id);

    @Query("select m from Major as m where m.professor.id = :id")
    List<AllMajorDto> findAllMajorById(@Param("id")String id);

    @Query("select m from Major as m where m.professor.id = :id and m.status = com.example.lms.lecture.domain.entity.Status.HOLDING")
    List<AllMajorDto> findApprovedMajorById(@Param("id")String id);

}
