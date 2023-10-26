package com.team.lms.mylecture.repository;

import com.team.lms.mylecture.domain.entity.MyLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyLectureRepository extends JpaRepository<MyLecture, Long> {
}
