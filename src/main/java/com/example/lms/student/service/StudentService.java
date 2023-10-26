package com.example.lms.student.service;

import com.example.lms.mylecture.repository.MyLectureRepository;
import com.example.lms.student.entity.Student;
import com.example.lms.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private MyLectureRepository myLectureRepository;


//    id로 학생정보 가져오기
    public Student getStudentById(UUID studentId){
        return studentRepository.findById(studentId).orElse(null);
    }



//    과제 제출 로직
}
