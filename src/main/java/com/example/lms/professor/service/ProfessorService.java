package com.example.lms.professor.service;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.professor.dto.ProfessorDto;
import com.example.lms.professor.repository.ProfessorRepository;
import com.example.lms.professor.entity.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;

//   교수 정보를 Id로 가져오기
    public ProfessorDto getProfessorById(String professorId){
        return professorRepository.findByProfessorId(professorId);
    }

    public void uploadLecture(UUID professorId, Lecture lecture){

    }
}
