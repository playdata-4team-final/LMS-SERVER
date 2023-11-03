package com.example.lms.student.controller;

import com.example.lms.student.dto.StudentRequest;

import com.example.lms.student.servcie.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lms")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody StudentRequest request){
        studentService.saveStudent(request);
    }
}
