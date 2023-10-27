package com.team.lms.lecture.service;

import com.team.lms.global.admin.repository.AdminRepository;
import com.team.lms.global.domain.response.LmsResponse;
import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.lecture.domain.entity.Semester;
import com.team.lms.lecture.domain.entity.Status;
import com.team.lms.lecture.domain.request.AdminLectureRequest;
import com.team.lms.lecture.domain.request.ProfessorLectureRequest;
import com.team.lms.lecture.domain.response.AllLectureRes;
import com.team.lms.lecture.repository.LectureRepository;
import com.team.lms.major.entity.Major;
import com.team.lms.professor.entity.Professor;
import com.team.lms.professor.repository.ProfessorRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest
class LectureServiceTest {


    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private ProfessorRepository professorRepository;


    @Test
    void requestLecture() {

        // 임의의 교수 생성
        Professor professor1 = Professor.builder()
                .id(UUID.fromString("7d6f858a-d8bd-4074-8c6e-d9c47e21b1a6"))
                .professorName("John Doe")
                .major(Major.builder().id(1L).build())
                .phNumber("123-456-7890")
                .email("john.doe@example.com")
                .build();

        // 교수 유저 저장
        professorRepository.save(professor1);

        // 임의의 ProfessorLectureRequest 객체 생성
        ProfessorLectureRequest request = new ProfessorLectureRequest(professor1.getId(), professor1.getMajor().getId());

        // Mock Repository의 동작 설정
        when(lectureRepository.findAllLectureById(any(UUID.class))).thenReturn(new ArrayList<>()); // 강의가 존재하지 않는 상태로 설정

        // 테스트 실행
        assertDoesNotThrow(() -> lectureService.requestLecture(request));

        // Verify - 강의가 저장되었는지 확인
        verify(lectureRepository, times(1)).save(any(Lecture.class));
    }



}

