package com.example.lms.student.controller;

import com.example.lms.global.domain.response.LmsResponse;
import com.example.lms.student.dto.StudentRequest;

import com.example.lms.student.servcie.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/lms")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public LmsResponse<String> saveStudent(@RequestBody StudentRequest request){
        String s = studentService.saveStudent(request);
        return new LmsResponse<>(HttpStatus.OK, s, "서비스 성공", "", LocalDateTime.now());
    }
}
