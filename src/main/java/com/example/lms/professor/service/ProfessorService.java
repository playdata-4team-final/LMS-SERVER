package com.example.lms.professor.service;

import com.example.lms.lecture.entity.Lecture;
import com.example.lms.professor.entity.Professor;
import com.example.lms.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;

//   교수 정보를 Id로 가져오기
    public Professor getProfessorById(UUID professorId){
        return professorRepository.findById(professorId).orElse(null);
    }

    public void uploadLecture(UUID professorId, Lecture lecture){

    }
}
