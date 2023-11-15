package com.example.lms.lecture.controller;

import com.example.lms.global.domain.response.LmsResponse;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.request.*;
import com.example.lms.lecture.domain.response.AllLectureRes;
import com.example.lms.lecture.domain.response.AllMajorRes;
import com.example.lms.lecture.dto.AllMajorDto;
import com.example.lms.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lecture")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LectureController {

    private final LectureService lectureService;



    //요청강의 조회(교수)
    @GetMapping("/findLecture/{id}")
    public LmsResponse<List<AllLectureRes>> agreeLectureFindById(@PathVariable("id") String id) {
        List<AllLectureRes> allLectureRes = lectureService.agreeLectureFindById(id);
        if (allLectureRes.size() ==0){
            return new LmsResponse<>(HttpStatus.OK, new ArrayList<>(), "서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, allLectureRes, "서비스 성공", "", LocalDateTime.now());
    }

    //요청전공 조회(교수)
    @GetMapping("/findMajor/{id}")
    public LmsResponse<List<AllMajorRes>> approvedMajorFindById(@PathVariable("id") String id) {
        List<AllMajorRes> allMajorRes = lectureService.approvedMajorFindById(id);
        if (allMajorRes.size()==0){
            return new LmsResponse<>(HttpStatus.OK, new ArrayList<>(), "서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, allMajorRes, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 요청(교수)
    @PostMapping("/requestLecture")
    public LmsResponse<String> requestLecture(@RequestBody ProfessorLectureRequest request) {
        Lecture lecture = lectureService.requestLecture(request);
        if (lecture == null){
            return new LmsResponse<>(HttpStatus.OK, "","서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, lecture.toString(), "서비스 성공", "", LocalDateTime.now());
    }

    //전공 요청(교수)
    @PostMapping("/requestMajor")
    public LmsResponse<String> requestMajor(@RequestBody ProfessorMajorRequest request) {
        String s = lectureService.requestMajor(request);

        if (s == "Failed RequestMajor"){
            return new LmsResponse<>(HttpStatus.OK, s,"서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //요청 전공 취소(교수)
    @PostMapping("/cancelMajor")
    public LmsResponse<String> cancelMajor(@RequestBody ProfessorMajorCancelRequest request) {
        String s = lectureService.cancelMajor(request);

        if (s ==null){
            return new LmsResponse<>(HttpStatus.OK, "","서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 요청 취소(교수)
    @PostMapping("/cancelLecture")
    public LmsResponse<String> cancelLecture(@RequestBody ProfessorLectureCancelRequest request) {
        String s = lectureService.cancelLecture(request);

        if (s ==null){
            return new LmsResponse<>(HttpStatus.OK, "", "서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 조회(어드민)
    @GetMapping("/findAllAcceptLectures")
    public LmsResponse<List<AllLectureRes>> findAllAcceptLecture() {
        List<AllLectureRes> lectures = lectureService.findAllAcceptLecture();
        if (lectures.size() ==0){
            return new LmsResponse<>(HttpStatus.OK, new ArrayList<>(), "서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, lectures, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 조회(모든 유저)
    @GetMapping("/findAllHoldingLectures")
    public LmsResponse<List<AllLectureRes>> findAllHoldingLecture() {
        List<AllLectureRes> lectures = lectureService.findAllHoldingLecture();
        if (lectures.size() ==0){
            return new LmsResponse<>(HttpStatus.OK, new ArrayList<>(), "서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, lectures, "서비스 성공", "", LocalDateTime.now());
    }

    //전공 조회(관리자)
    @GetMapping("/findAllMajors")
    public LmsResponse<List<AllMajorRes>> findAllMajors() {
        List<AllMajorRes> allMajors = lectureService.findAllMajors();

        if (allMajors ==null){
            return new LmsResponse<>(HttpStatus.OK, new ArrayList<>(), "서비스 에러", "", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, allMajors, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 수락(관리자)
    @PostMapping("/acceptLecture")
    public LmsResponse<String> acceptLecture(@RequestBody AdminLectureRequest request) {
        String s = lectureService.acceptLecture(request);

        if (s ==null){
            return new LmsResponse<>(HttpStatus.OK, "", "서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }
    //강의 수락(관리자)
    @PostMapping("/acceptMajor")
    public LmsResponse<String> acceptMajor(@RequestBody AdminMajorRequest request) {
        String s = lectureService.acceptMajor(request);

        if (s ==null){
            return new LmsResponse<>(HttpStatus.OK, "", "서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

    //강의 거절(관리자)
    @PostMapping("/denyMajor")
    public LmsResponse<String> denyMajor(@RequestBody AdminMajorRequest request) {
        String s = lectureService.denyMajor(request);
        if (s ==null){
            return new LmsResponse<>(HttpStatus.OK, "", "서비스 실패", "에러 발생", LocalDateTime.now());
        }
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }

}





