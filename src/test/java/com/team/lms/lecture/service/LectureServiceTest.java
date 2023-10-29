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

    @InjectMocks
    private LectureService lectureService;

    @BeforeEach
    public void setUp() {


        // 임의의 교수 생성
//        Professor professor1 = Professor.builder()
//                .id(UUID.fromString("7d6f858a-d8bd-4074-8c6e-d9c47e21b1a6"))
//                .professorName("오성")
//                .phNumber("010-0000-0000")
//                .email("john.doe@example.com")
//                .build();

        // 임의의 Major 객체 생성
//        Major major = Major.builder()
//                .checkMajor(true) // 전공 여부
//                .majorName("컴퓨터 공학") // 전공 이름
//                .status(Status.HOLDING) // 상태
//                .professor(professor1) // 교수 연결
//                .build();

        // 교수 유저 저장
//        professorRepository.save(professor1);
//        majorRepository.save(major);
    }

    @Transactional
    @Test
    void requestLecture(){

//        Optional<Professor> byId = professorRepository.findById(UUID.fromString("7d6f858a-d8bd-4074-8c6e-d9c47e21b1a6"));
        Optional<Major> byId2 = majorRepository.findById(1L);

        //데이터 저장 확인
//        String professorName = byId.get().getProfessorName();
        String majorName = byId2.get().getMajorName();
//        System.out.println("교수 이름: " + professorName);
        System.out.println("전공 이름: " + majorName);

        // 임의의 ProfessorLectureRequest 객체 생성
//        ProfessorLectureRequest request = new ProfessorLectureRequest(byId.get().getId(), byId.get().getMajors().get(0).getId(), "자바", 30, 4, Semester.SECOND );

        // Mock Repository의 동작 설정
//        when(lectureRepository.findAllLectureById().thenReturn(new ArrayList<>()); // 강의가 존재하지 않는 상태로 설정

        // 테스트 실행
//        assertDoesNotThrow(() -> lectureService.requestLecture(request));

        // Verify - 강의가 저장되었는지 확인
        verify(lectureRepository, times(1)).save(any(Lecture.class));
    }
}

