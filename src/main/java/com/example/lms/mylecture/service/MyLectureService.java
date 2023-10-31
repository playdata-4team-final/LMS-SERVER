package com.example.lms.mylecture.service;

import com.example.lms.mylecture.repository.MyLectureRepository;
import com.example.lms.mylecture.domain.entity.MyLecture;
import com.example.lms.mylecture.domain.request.MyLectureRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyLectureService {


    private final MyLectureRepository myLectureRepository;

    @Transactional
    public List<MyLecture> getMyLecturesByStudent(String studentId) {
        return myLectureRepository.findByStudentId(studentId);
    }
    @Transactional
    public List<MyLecture> getMyLecturesByProfessor(String professorId) {
        return myLectureRepository.findByProfessorId(professorId);
    }
    @Transactional
    public List<MyLecture> getMyLecturesByLecture(Long lectureId){
        return myLectureRepository.findByLectureId(lectureId);
    }
    @Transactional
    public MyLecture createMyLecture(MyLectureRequest request) {

        // 요청을 MyLecture 엔티티로 변환하여 저장
        MyLecture myLecture = request.toEntity();
        return myLectureRepository.save(myLecture);
    }
}
