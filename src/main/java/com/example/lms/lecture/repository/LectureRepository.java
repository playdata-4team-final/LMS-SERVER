package com.example.lms.lecture.repository;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.dto.AllLectureDto;
import com.example.lms.schedule.entity.WeekDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LectureRepository
        extends JpaRepository<Lecture, Long> {


    //major, professor, room을 가지고 외래키로 가지고 있어서 정보를 한번에 볼려면 조인해야함.
    //그 중에서도 major는 이름만, professor는 교수 이름만, room은 강의실 넘버만 가져오고싶음
    @Query("SELECT new com.example.lms.lecture.dto.AllLectureDto(l.id, l.lectureName, l.status, l.maximumNumber, l.score, l.lectureComment, l.lectureDate, l.semester, l.major.majorName, l.professor.professorName) FROM Lecture l WHERE l.professor.id = :id")
    List<AllLectureDto> findAllLectureDTOsByProfessorId(@Param("id") String id);

    @Query("select new com.example.lms.lecture.dto.AllLectureDto(l.id, l.lectureName, l.status, l.maximumNumber, l.score, l.lectureComment, l.lectureDate, l.semester, l.major.majorName, l.professor.professorName) FROM Lecture as l where l.status = com.example.lms.lecture.domain.entity.Status.HOLDING")
    List<AllLectureDto> findAllHoldingList();


    @Query("select new com.example.lms.lecture.dto.AllLectureDto(l.id, l.lectureName, l.status, l.maximumNumber, l.score, l.lectureComment, l.lectureDate, l.semester, l.major.majorName, l.professor.professorName) FROM Lecture as l where l.status = com.example.lms.lecture.domain.entity.Status.ACCEPT")
    List<AllLectureDto> findAllAcceptList();

    @Modifying
    @Query("DELETE FROM Lecture l WHERE l.professor.id = :professorId")
    void deleteByProfessorId(@Param("professorId") String professorId);



}
