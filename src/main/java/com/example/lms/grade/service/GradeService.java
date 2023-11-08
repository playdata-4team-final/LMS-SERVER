package com.example.lms.grade.service;

import com.example.lms.grade.dto.GradeRequest;
import com.example.lms.grade.dto.ProfessorLectureList;
import com.example.lms.grade.dto.ProfessorGradeResponse;
import com.example.lms.grade.dto.StudentGradeResponse;
import com.example.lms.grade.entity.Credit;
import com.example.lms.grade.entity.Grade;
import com.example.lms.grade.repository.GradeRepository;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.repository.LectureRepository;
import com.example.lms.student.entity.Student;
import com.example.lms.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    // 학생 성적조회
    public List<StudentGradeResponse> getGradeList(String memberId) {
        List<Grade> gradeList = gradeRepository.findByStudentId(memberId);
        return gradeList.stream()
                .map(grade -> new StudentGradeResponse(grade, calculateTotalScore(gradeList)))
                .toList();
    }

    // 평균 구하기
    private Double calculateTotalScore(List<Grade> gradeList) {
        double totalScore = gradeList.stream()
                .mapToDouble(Grade::getScore)
                .sum();

        if (gradeList.isEmpty()) {
            return 0.0;
        } else {
            return totalScore / gradeList.size();
        }
    }

//    // 자신의 강의 조회 (교수)
//    public List<ProfessorLectureList> getMyLectureList(String memberId) {
//        lectureRepository.findAllLectureDTOsByProfessorId(memberId);
//        return null;
//    }

    // 특정과목 성적조회 (교수)
    public List<ProfessorGradeResponse> getProfessorGradeList(Long id, String memberId) {
        List<Grade> gradeList = gradeRepository.findByLectureId(id);
        return gradeList.stream()
                .map(ProfessorGradeResponse::new)
                .toList();
    }
    @Transactional
    public void saveGrade(Long id, List<GradeRequest> requests) {

        // 성적 저장
        for (GradeRequest request : requests) {
            Optional<Student> optionalStudent = studentRepository.findById(request.getStudentId());
            Optional<Lecture> optionalLecture = lectureRepository.findById(id);
            gradeRepository.save(request.toEntity(optionalLecture.get(),
                    optionalStudent.get(), request.getScore()));
        }

        calculateCredit(id);
    }
    @Transactional
    public void calculateCredit(Long id) {
        List<Grade> allGradesForLecture = gradeRepository.findAllByLectureId(id);
        allGradesForLecture.sort(Comparator.comparing(Grade::getScore).reversed());

        int totalStudents = allGradesForLecture.size();
        double percentile = 0;
        double currentPercentile = 0.0;

        for (int i = 0; i < totalStudents; i++) {
            Grade grade = allGradesForLecture.get(i);

            if(i > 0 && Double.compare(grade.getScore(), allGradesForLecture.get(i - 1).getScore()) == 0){
                percentile = currentPercentile;
            } else {
                percentile = (i + 1) / (double) totalStudents * 100.0;
                currentPercentile = percentile;
            }

            grade.saveCredit(assignGrade(percentile));
        }
    }

    private Credit assignGrade(double percentile) {
        if (percentile <= 10) {
            return Credit.valueOf("A");
        } else if (percentile <= 30) {
            return Credit.valueOf("B");
        } else if (percentile <= 50) {
            return Credit.valueOf("C");
        } else if (percentile <= 70) {
            return Credit.valueOf("D");
        } else {
            return Credit.valueOf("F");
        }
    }

    // 성적수정 (교수)
    @Transactional
    public void updateGrade(Long id, GradeRequest request) {
        Optional<Student> optionalStudent = studentRepository.findById(request.getStudentId());
        Optional<Lecture> optionalLecture = lectureRepository.findById(id);
        Student student = optionalStudent.get();
        Lecture lecture = optionalLecture.get();
        Grade grade = gradeRepository.findByStudentAndLecture(student,lecture);
        grade.updateScore(request.getScore());
        calculateCredit(id);
    }
}
