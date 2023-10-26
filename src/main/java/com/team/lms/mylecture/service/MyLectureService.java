package com.team.lms.mylecture.service;

import com.team.lms.lecture.repository.LectureRepository;
import com.team.lms.mylecture.repository.MyLectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyLectureService {
    private final MyLectureRepository myLectureRepository;
    private final LectureRepository lectureRepository;
    public void createMyLecture(){

    }
}
