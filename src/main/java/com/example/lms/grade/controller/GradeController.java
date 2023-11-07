package com.example.lms.grade.controller;

import com.example.lms.domain.response.LmsResponse;
import com.example.lms.grade.dto.GradeRequest;
import com.example.lms.grade.dto.ProfessorGradeResponse;
import com.example.lms.grade.dto.StudentGradeResponse;
import com.example.lms.grade.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grade")
public class GradeController {

    private final GradeService gradeService;

    // 성적조회 (학생)
    @GetMapping
    public LmsResponse<List<StudentGradeResponse>> getStudentGradeList(@RequestHeader(value = "member-id") String memberId) {
        List<StudentGradeResponse> response = gradeService.getGradeList(memberId);
        return new LmsResponse<>(HttpStatus.OK, response, "조회 성공", "", LocalDateTime.now());
    }

    // 자신의 강의 조회 (교수)
//    @GetMapping("")
//    public LmsResponse<List<ProfessorLectureList>> getMyLectureList(@RequestHeader(value = "member-id") String memberId){
//        List<ProfessorLectureList> response = gradeService.getMyLectureList(memberId);
//        return null;
//    }

    // 특정과목 성적조회 (교수)
    @GetMapping("/{id}")
    public LmsResponse<List<ProfessorGradeResponse>> getProfessorGradeList(@PathVariable(value = "id") Long id,
                                                                           @RequestHeader(value = "member-id") String memberId) {
        List<ProfessorGradeResponse> response = gradeService.getProfessorGradeList(id, memberId);
        return new LmsResponse<>(HttpStatus.OK, response, "조회 성공", "", LocalDateTime.now());
    }

    // 특정과목 성적입력 (교수)
    @PostMapping("/{id}")
    public void saveGrade(@PathVariable(value = "id") Long id,
                          @RequestBody List<GradeRequest> requests) {
        gradeService.saveGrade(id, requests);
    }

    // 성적수정 (교수)
    @PutMapping("/{id}")
    public void updateGrade(@PathVariable(value = "id") Long id,
                            @RequestBody GradeRequest request) {
        gradeService.updateGrade(id, request);
    }
}



