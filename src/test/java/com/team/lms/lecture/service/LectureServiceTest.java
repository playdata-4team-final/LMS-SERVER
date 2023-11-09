package com.example.lms.lecture.service;

import com.example.lms.admin.repository.AdminRepository;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Semester;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.lecture.domain.request.ProfessorLectureRequest;
import com.example.lms.lecture.repository.LectureRepository;
import com.example.lms.major.entity.Major;
import com.example.lms.major.repository.MajorRepository;
import com.example.lms.professor.entity.Professor;
import com.example.lms.professor.repository.ProfessorRepository;

import com.example.lms.room.repository.RoomRepository;
import com.example.lms.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @MockBean
    private MajorRepository majorRepository;
    @MockBean
    private ProfessorRepository professorRepository;

    @MockBean LectureRepository lectureRepository;

    @MockBean
    ScheduleRepository scheduleRepository;

    @MockBean
    RoomRepository roomRepository;
    @InjectMocks
    private LectureService lectureService;

    @BeforeEach
    public void setUp() {


//         임의의 교수 생성
        Professor professor1 = Professor.builder()
                .id("7d6f858a-d8bd-4074-8c6e-d9c47e21b1a6")
                .professorName("오성")
                .phNumber("010-0000-0000")
                .email("john.doe@example.com")
                .build();

//         임의의 Major 객체 생성
        Major major = Major.builder()
                .checkMajor(true) // 전공 여부
                .majorName("컴퓨터 공학") // 전공 이름
                .status(Status.HOLDING) // 상태
                .professor(professor1) // 교수 연결
                .build();

//         교수 유저 저장
        professorRepository.save(professor1);
        majorRepository.save(major);

    }

    @Transactional
    @Test
    void requestLecture(){


    }


}

