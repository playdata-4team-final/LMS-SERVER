package com.example.lms.student.servcie;

import com.example.lms.student.dto.StudentRequest;
import com.example.lms.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public void saveStudent(StudentRequest request){
        repository.save(request.toEntity());
    }

}
