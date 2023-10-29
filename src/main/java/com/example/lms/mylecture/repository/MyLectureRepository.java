package com.example.lms.mylecture.repository;

import com.example.lms.mylecture.domain.entity.MyLecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MyLectureRepository extends JpaRepository<MyLecture, Long> {
    List<MyLecture> findByStudentId(String studentId);

    List<MyLecture> findByProfessorId(String professorId);

    List<MyLecture> findByLectureId(Long lectureId);
}
