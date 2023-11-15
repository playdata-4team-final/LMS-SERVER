package com.example.lms.lecture.domain.request;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Semester;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.major.entity.Major;
import com.example.lms.professor.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorLectureCancelRequest {

   private List<Long> lectureIds;


    public List<Lecture> toEntity(){
        return lectureIds.stream()
                .map(id-> Lecture.builder().id(id).build())
                .collect(Collectors.toList());
    }

}
