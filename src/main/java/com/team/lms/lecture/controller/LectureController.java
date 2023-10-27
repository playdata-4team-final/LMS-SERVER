package com.team.lms.lecture.controller;


import com.team.lms.global.domain.response.LmsResponse;
import com.team.lms.lecture.domain.request.AdminLectureRequest;
import com.team.lms.lecture.domain.request.ProfessorLectureRequest;
import com.team.lms.lecture.domain.request.ProfessorMajorRequest;
import com.team.lms.lecture.domain.response.AllLectureRes;
import com.team.lms.lecture.domain.response.AllMajorRes;
import com.team.lms.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/findLecture")
    public ResponseEntity<LmsResponse<List<AllLectureRes>>> agreeLectureFindById(@RequestParam("id") UUID id) {
        LmsResponse<List<AllLectureRes>> listLmsResponse = lectureService.agreeLectureFindById(id);
        HttpStatus status = listLmsResponse.getCode();
        List<AllLectureRes> lectures = listLmsResponse.getData();
        return ResponseEntity.ok(new LmsResponse<>(status, lectures, null, LocalDateTime.now()));
    }

    @GetMapping("/findMajor")
    public ResponseEntity<LmsResponse<List<AllMajorRes>>> agreeMajorFindById(@RequestParam("id") UUID id) {
        List<AllMajorRes> majors = lectureService.agreeMajorFindById(id).getData();
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, majors, null, LocalDateTime.now()));
    }

    @PostMapping("/requestLecture")
    public ResponseEntity<LmsResponse<Void>> requestLecture(@RequestBody ProfessorLectureRequest request) {

        lectureService.requestLecture(request);
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, LocalDateTime.now()));
    }

    @PostMapping("/requestMajor")
    public ResponseEntity<LmsResponse<Void>> requestMajor(@RequestBody ProfessorMajorRequest request){

        lectureService.requestMajor(request);
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, LocalDateTime.now()));
    }

    @DeleteMapping("/cancelMajor")
    public ResponseEntity<LmsResponse<Void>> cancelMajor(@RequestBody ProfessorMajorRequest request) {
        lectureService.canceltMajor(request);
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, LocalDateTime.now()));
    }

    @DeleteMapping("/cancelLecture")
    public ResponseEntity<LmsResponse<Void>> cancelLecture(@RequestBody ProfessorLectureRequest request) {
        lectureService.cancelLecture(request);
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, LocalDateTime.now()));
    }

    @GetMapping("/findAllLecture")
    public ResponseEntity<LmsResponse<List<AllLectureRes>>> findAllLecture() {
        List<AllLectureRes> lectures = lectureService.findAllLecture().getData();
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, lectures, null, LocalDateTime.now()));
    }

    @PostMapping("/acceptLecture")
    public ResponseEntity<LmsResponse<Void>> acceptLecture(@RequestBody AdminLectureRequest request) {
        lectureService.acceptLecture(request.getAdminId());
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, LocalDateTime.now()));
    }

}





