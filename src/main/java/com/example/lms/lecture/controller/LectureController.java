package com.example.lms.lecture.controller;


import com.example.lms.domain.response.LmsResponse;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.request.AdminLectureRequest;
import com.example.lms.lecture.domain.request.AdminMajorRequest;
import com.example.lms.lecture.domain.request.ProfessorLectureRequest;
import com.example.lms.lecture.domain.request.ProfessorMajorRequest;
import com.example.lms.lecture.domain.response.AllLectureRes;
import com.example.lms.lecture.domain.response.AllMajorRes;
import com.example.lms.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    //요청강의 조회(교수)
    @GetMapping("/findLecture")
    public ResponseEntity<LmsResponse<List<AllLectureRes>>> agreeLectureFindById(@RequestParam("id") String id) {
        ResponseEntity<LmsResponse<List<AllLectureRes>>> lmsResponseResponseEntity = lectureService.agreeLectureFindById(id);
        HttpStatus status = lmsResponseResponseEntity.getBody().getCode();
        List<AllLectureRes> dataList = lmsResponseResponseEntity.getBody().getData();
        String errorMsg = lmsResponseResponseEntity.getBody().getErrorMsg();
        if (dataList==null){
            return ResponseEntity.ok(new LmsResponse<>(status, new ArrayList<>(), "서비스 에러", errorMsg, LocalDateTime.now()));
        }
        return ResponseEntity.ok(new LmsResponse<>(status, dataList, "에러 없음", "서비스 성공", LocalDateTime.now()));
    }

    //요청전공 조회(교수)
    @GetMapping("/findApprovedMajor")
    public ResponseEntity<LmsResponse<List<AllMajorRes>>> approvedMajorFindById(@RequestParam("id") String id) {
        ResponseEntity<LmsResponse<List<AllMajorRes>>> lmsResponseResponseEntity = lectureService.approvedMajorFindById(id);
        HttpStatus status = lmsResponseResponseEntity.getBody().getCode();
        List<AllMajorRes> dataList = lmsResponseResponseEntity.getBody().getData();
        String errorMsg = lmsResponseResponseEntity.getBody().getErrorMsg();
        if (dataList==null){
            return ResponseEntity.ok(new LmsResponse<>(status, new ArrayList<>(), "서비스 에러", errorMsg, LocalDateTime.now()));
        }
        return ResponseEntity.ok(new LmsResponse<>(status, dataList, "에러 없음", "서비스 성공", LocalDateTime.now()));
    }

    //강의 요청(교수)
    @PostMapping("/requestLecture")
    public ResponseEntity<LmsResponse<String>> requestLecture(@RequestBody ProfessorLectureRequest request) {
        ResponseEntity<LmsResponse<Lecture>> lmsResponseResponseEntity = lectureService.requestLecture(request);
        HttpStatus status = lmsResponseResponseEntity.getBody().getCode();
        Lecture data = lmsResponseResponseEntity.getBody().getData();
        String errorMsg = lmsResponseResponseEntity.getBody().getErrorMsg();
        if (lmsResponseResponseEntity.getStatusCode()!=HttpStatus.OK){
            return ResponseEntity.ok(new LmsResponse<>(status, "","서비스 에러",errorMsg, LocalDateTime.now()));
        }
        return ResponseEntity.ok(new LmsResponse<>(status, data.toString(), "서비스 성공","에러 없음", LocalDateTime.now()));
    }

    //전공 요청(교수)
    @PostMapping("/requestMajor")
    public ResponseEntity<LmsResponse<String>> requestMajor(@RequestBody List<ProfessorMajorRequest> requests) {
        ResponseEntity<LmsResponse<Object>> lmsResponseResponseEntity = lectureService.requestMajor(requests);
        HttpStatus status = lmsResponseResponseEntity.getBody().getCode();
        Object data = lmsResponseResponseEntity.getBody().getData();
        String errorMsg = lmsResponseResponseEntity.getBody().getErrorMsg();
        if (data ==null){
            return ResponseEntity.ok(new LmsResponse<>(status, "", "서비스 에러", errorMsg, LocalDateTime.now()));
        }
        return ResponseEntity.ok(new LmsResponse<>(status, data.toString(), "서비스 성공", "에러 없음", LocalDateTime.now()));
    }

    //요청 전공 취소(교수)
    @PostMapping("/cancelMajor")
    public ResponseEntity<LmsResponse<String>> cancelMajor(@RequestBody List<ProfessorMajorRequest> requests) {
        ResponseEntity<LmsResponse<String>> lmsResponseResponseEntity = lectureService.cancelMajor(requests);
        HttpStatus status = lmsResponseResponseEntity.getBody().getCode();
        String data = lmsResponseResponseEntity.getBody().getData();
        String errorMsg = lmsResponseResponseEntity.getBody().getErrorMsg();
        if (data ==null){
            return ResponseEntity.ok(new LmsResponse<>(status, "", "서비스 에러", errorMsg, LocalDateTime.now()));
        }
        return ResponseEntity.ok(new LmsResponse<>(status, data, "서비스 성공", "에러 없음", LocalDateTime.now()));
    }
    //강의 요청 취소(교수)
    @DeleteMapping("/cancelLecture")
    public ResponseEntity<LmsResponse<Void>> cancelLecture(@RequestBody ProfessorLectureRequest request) {
        lectureService.cancelLecture(request);

        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, null , LocalDateTime.now()));
    }

    //강의 조회(관리자)
    @GetMapping("/findAllLectures")
    public ResponseEntity<LmsResponse<List<AllLectureRes>>> findAllLecture() {
        List<AllLectureRes> lectures = lectureService.findAllLecture().getData();
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, lectures, null, null, LocalDateTime.now()));
    }

    //전공 조회(관리자)
    @GetMapping("/findAllMajors")
    public ResponseEntity<LmsResponse<List<AllMajorRes>>> findAllMajors() {
        HttpStatus status = lectureService.findAllMajors().getBody().getCode();
        List<AllMajorRes> dataList = lectureService.findAllMajors().getBody().getData();
        String errorMsg = lectureService.findAllMajors().getBody().getErrorMsg();
        if (dataList ==null){
            return ResponseEntity.ok(new LmsResponse<>(status, new ArrayList<>(), "서비스 에러", errorMsg, LocalDateTime.now()));
        }
        return ResponseEntity.ok(new LmsResponse<>(status, dataList, "서비스 성공", "에러없음", LocalDateTime.now()));
    }



    //강의 수락(관리자)
    @PostMapping("/acceptLecture")
    public ResponseEntity<LmsResponse<Void>> acceptLecture(@RequestBody AdminLectureRequest request) {
        lectureService.acceptLecture(request);
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, null, LocalDateTime.now()));
    }

    //강의 수락(관리자)
    @PostMapping("/acceptMajor")
    public ResponseEntity<LmsResponse<String>> acceptMajor(@RequestBody AdminMajorRequest request) {
        ResponseEntity<LmsResponse<String>> lmsResponseResponseEntity = lectureService.acceptMajor(request);
        HttpStatus status = lmsResponseResponseEntity.getBody().getCode();
        String data = lmsResponseResponseEntity.getBody().getData();
        String errorMsg = lmsResponseResponseEntity.getBody().getErrorMsg();
        if (data ==null){
            return ResponseEntity.ok(new LmsResponse<>(status, ""  , "서비스 에러", errorMsg, LocalDateTime.now()));
        }
        return ResponseEntity.ok(new LmsResponse<>(status, data, "서비스 성공", "에러없음", LocalDateTime.now()));
    }


    //강의 거절(관리자)
    @PostMapping("/denyMajor")
    public ResponseEntity<LmsResponse<String>> denyMajor(@RequestBody AdminMajorRequest request) {
        ResponseEntity<LmsResponse<String>> lmsResponseResponseEntity = lectureService.acceptMajor(request);
        HttpStatus status = lmsResponseResponseEntity.getBody().getCode();
        String data = lmsResponseResponseEntity.getBody().getData();
        String errorMsg = lmsResponseResponseEntity.getBody().getErrorMsg();
        if (data ==null){
            return ResponseEntity.ok(new LmsResponse<>(status, ""  , "서비스 에러", errorMsg, LocalDateTime.now()));
        }
        return ResponseEntity.ok(new LmsResponse<>(status, data, "서비스 성공", "에러없음", LocalDateTime.now()));
    }
}





