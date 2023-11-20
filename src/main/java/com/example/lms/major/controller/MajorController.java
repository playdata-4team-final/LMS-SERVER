package com.example.lms.major.controller;

import com.example.lms.global.domain.response.LmsResponse;
import com.example.lms.major.entity.Major;
import com.example.lms.major.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/major")
@RequiredArgsConstructor
public class MajorController {

    private final MajorService majorService;

    @GetMapping("/lists")
    public LmsResponse<List<Major>> getAll(){
        List<Major> all = majorService.getAll();
        return new LmsResponse<>(HttpStatus.OK, all, "서비스 성공", "", LocalDateTime.now());
    }

}
