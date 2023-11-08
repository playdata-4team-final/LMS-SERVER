package com.example.lms.grade.repository;

import com.example.lms.grade.entity.Grade;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findByStudentId(String memberId);

    List<Grade> findByLectureId(Long id);

    List<Grade> findAllByLectureId(Long id);

    Grade findByStudentAndLecture(Student student, Lecture lecture);
}
