package com.team.lms.lecture.controller;

import com.team.lms.global.domain.request.LmsRequest;
import com.team.lms.global.domain.response.LmsResponse;
import com.team.lms.lecture.domain.request.AdminLectureRequest;
import com.team.lms.lecture.domain.request.ProfessorLectureRequest;
import com.team.lms.lecture.domain.response.AllLectureRes;
import com.team.lms.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lecture")
@RequiredArgsConstructor
public class LectureContoller {


    private final LectureService lectureService;

    // 강의 요청 목록 조회(교수)
    @GetMapping("/findLecture")
    public LmsResponse<List<AllLectureRes>> agreeLectureFindById(@RequestParam("id") UUID id){
        return lectureService.agreeLectureFindById(id);
    }


    //강의 등록 요청(교수)
    @PostMapping("/requestLecture")
    public void requestLecture(@RequestBody LmsRequest<ProfessorLectureRequest> request){ lectureService.requestLecture(request); }

    //강의 등록 요청 취소(교수)
    @DeleteMapping("/cancelLecture")
    public void cancelLecture(@RequestBody LmsRequest<ProfessorLectureRequest> request){ lectureService.cancelLecture(request); }


    // 강의 요청 목록 전체 조회(어드민)
    @GetMapping("/findAllLecture")
    public LmsResponse<List<AllLectureRes>> findAllLecture() {
        return lectureService.findAllLecture();
    }

    //강의 요청 승인(어드민)
    @PostMapping("/acceptLecture")
    public void acceptLecture(@RequestBody LmsRequest<AdminLectureRequest> request) {
        lectureService.acceptLecture(request.getRequest().getAdminId());
    }

    //강의 요청 거부(어드민)
    @DeleteMapping("/denyLecture")
    public void denyClass(@RequestBody LmsRequest<AdminLectureRequest> request){ lectureService.denyLecture(request); }

}
