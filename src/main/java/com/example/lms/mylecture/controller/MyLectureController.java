package com.example.lms.mylecture.controller;

import com.example.lms.global.domain.response.LmsResponse;
import com.example.lms.mylecture.domain.entity.MyLecture;
import com.example.lms.mylecture.domain.request.MyLectureRequest;
import com.example.lms.mylecture.domain.request.MyScheduleRequest;
import com.example.lms.mylecture.service.MyLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/my-lecture")
@RequiredArgsConstructor
public class MyLectureController {
    private final MyLectureService myLectureService;
    // 학생이 수강 중인 내강의 목록 조회


    @GetMapping("/students/{studentId}")
    public ResponseEntity<List<MyLecture>> getMyLecturesByStudent(@PathVariable("studentId") String studentId) {
        List<MyLecture> myLectures = myLectureService.getMyLecturesByStudent(studentId);
        return new ResponseEntity<>(myLectures, HttpStatus.OK);
    }
    // 교수가 진행 중인 내강의 목록 조회
    @GetMapping("/professors/{professorId}")
    public ResponseEntity<List<MyLecture>> getMyLecturesByProfessor(@PathVariable("professorId") String professorId) {
        List<MyLecture> myLectures = myLectureService.getMyLecturesByProfessor(professorId);
        return new ResponseEntity<>(myLectures, HttpStatus.OK);
    }

    //내 강의 생성
    @PostMapping("/createMyLecture")
    public ResponseEntity<MyLecture> createMyLecture(@RequestBody MyLectureRequest request) {
        MyLecture myLecture = myLectureService.createMyLecture(request);
        return new ResponseEntity<>(myLecture, HttpStatus.CREATED);
    }


    //스케줄 생성
    @PostMapping("/saveWeeklyData")
    public LmsResponse<String> saveWeeklyData(@RequestBody MyScheduleRequest request) {
        String s = myLectureService.saveWeeklyData(request);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

}
