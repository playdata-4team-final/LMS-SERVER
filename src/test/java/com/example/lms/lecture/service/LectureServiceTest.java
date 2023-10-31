package com.example.lms.lecture.service;

import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.lecture.repository.LectureRepository;
import com.example.lms.major.entity.Major;
import com.example.lms.major.repository.MajorRepository;
import com.example.lms.professor.entity.Professor;
import com.example.lms.professor.repository.ProfessorRepository;
import com.example.lms.room.repository.RoomRepository;
import com.example.lms.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LectureServiceTest {
    @MockBean
    private MajorRepository majorRepository;
    @MockBean
    private ProfessorRepository professorRepository;

    @MockBean
    LectureRepository lectureRepository;

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

    @Test
    void requestLecture() {
    }

    @Test
    void requestMajor() {
    }

    @Test
    void cancelMajor() {
    }

    @Test
    void approvedMajorFindById() {
    }

    @Test
    void agreeLectureFindById() {
    }

    @Test
    void cancelLecture() {
    }

    @Test
    void findAllHoldingLecture() {
    }

    @Test
    void findAllAcceptLecture() {
    }

    @Test
    void denyLecture() {
    }

    @Test
    void findAllMajors() {
    }

    @Test
    void acceptMajor() {
    }

    @Test
    void denyMajor() {
    }

    @Test
    void acceptLecture() {
    }

    @Test
    void changeAcceptLecture() {
    }

    @Test
    void changeAcceptMajor() {
    }

    @Test
    void changeDenyLecture() {
    }

    @Test
    void changeDenyMajor() {
    }

    @Test
    void deleteMajorForeignKey() {
    }
}