package com.example.lms.major.service;

import com.example.lms.major.entity.Major;
import com.example.lms.major.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;

    public List<Major> getAll() {
        List<Major> all = majorRepository.findAll();
        return  all;
    }
}
