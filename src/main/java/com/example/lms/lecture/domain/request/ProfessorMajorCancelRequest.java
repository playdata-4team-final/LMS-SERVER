package com.example.lms.lecture.domain.request;

import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.major.entity.Major;
import com.example.lms.major.repository.MajorRepository;
import com.example.lms.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorMajorCancelRequest {



    private List<Long> majorIds;


    public List<Major> toEntity(){
        return majorIds.stream()
                .map(id-> Major.builder().id(id).build())
                .collect(Collectors.toList());
    }


}
