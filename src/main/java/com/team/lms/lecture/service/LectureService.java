package com.team.lms.lecture.service;


import com.team.lms.example.domain.response.LmsResponse;
import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.lecture.domain.entity.Status;
import com.team.lms.lecture.domain.request.AdminLectureRequest;
import com.team.lms.lecture.domain.request.ProfessorLectureRequest;
import com.team.lms.lecture.domain.request.ProfessorMajorRequest;
import com.team.lms.lecture.domain.response.AllLectureRes;
import com.team.lms.lecture.domain.response.AllMajorRes;
import com.team.lms.lecture.exception.DuplicateException;
import com.team.lms.lecture.exception.NotFoundException;
import com.team.lms.lecture.repository.LectureRepository;
import com.team.lms.major.entity.Major;
import com.team.lms.major.repository.MajorRepository;
import com.team.lms.professor.entity.Professor;
import com.team.lms.professor.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LectureService {

    @Autowired
    private final LectureRepository lectureRepository;

    @Autowired
    private final MajorRepository majorRepository;

    @Autowired
    private final ProfessorRepository professorRepository;

    //강의 등록 요청(교수)
    @Transactional
    public ResponseEntity<LmsResponse<Void>> requestLecture(ProfessorLectureRequest request){
        UUID professorId = request.getProfessorId();
        Long majorId = request.getMajorId();
        Professor existingProfessor = professorRepository.findById(professorId).orElse(null);
        Major existingMajor = majorRepository.findById(majorId).orElse(null);
        List<Lecture> allLectureById = lectureRepository.findAllLectureById(professorId);

        if(!allLectureById.isEmpty() ) {
            if (allLectureById.get(0).getMajor().getMajorName().equals(existingMajor.getMajorName())) {
                throw new DuplicateException("이미 신청했거나 대기중인 전공입니다.");
            }
        }//DUPLICATE ERROR


        if (existingProfessor == null || existingMajor == null) {
            throw new NotFoundException("해당되는 유저나 전공이 없습니다."); //NOT FOUND ERROR
        }

        Lecture newLecture = Lecture.builder()
                .lectureName(request.toEntity().getLectureName())
                .status(request.toEntity().getStatus())
                .maximumNumber(request.toEntity().getMaximumNumber())
                .score(request.toEntity().getScore())
                .lectureComment(request.toEntity().getLectureComment())
                .lectureDate(request.toEntity().getLectureDate())
                .semester(request.toEntity().getSemester())
                .professor(existingProfessor) // 기존 Professor를 참조합니다.
                .major(existingMajor) // 기존 Major를 참조합니다.
                .build();

        lectureRepository.save(newLecture);
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, LocalDateTime.now()));
    }

    //전공 등록 요청 교수(교수)
    @Transactional
    public ResponseEntity<LmsResponse<Void>> requestMajor(List<ProfessorMajorRequest> requests) {
        for (ProfessorMajorRequest request : requests) {
            UUID professorId = request.getProfessorId();

            // 교수 정보 가져오기
            Professor professor = professorRepository.findById(professorId)
                    .orElseThrow(() -> new NotFoundException("해당 교수를 찾을 수 없습니다."));

            // 전공 생성
            List<String> majorNames = request.getMajorNames();
            List<Major> majorList = new ArrayList<>();

            for (String majorName : majorNames) {
                Major major = Major.builder()
                        .professor(professor)
                        .majorName(majorName)
                        .checkMajor(request.getCheckMajor())
                        .status(Status.HOLDING)
                        .build();

                majorList.add(major);
            }

            // 전공 리스트 저장
            majorRepository.saveAll(majorList);
        }

        // 응답 반환
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, LocalDateTime.now()));
    }


    //전공 등록 요청 취소(교수)
        @Transactional
        public ResponseEntity<LmsResponse<Void>> cancelMajor(List<ProfessorMajorRequest> requests){

            for (ProfessorMajorRequest request : requests) {
                UUID professorId = request.getProfessorId();

                // 교수 정보 가져오기
                Professor professor = professorRepository.findById(professorId)
                        .orElseThrow(() -> new NotFoundException("해당 교수를 찾을 수 없습니다."));

                List<String> majorNames = request.getMajorNames();
                if (!majorNames.isEmpty()) {
                    String majorName = majorNames.get(0);

                    // professorId와 majorName으로 전공 삭제
                    majorRepository.deleteByMajorNameAndProfessorId(professorId,majorName);
                }
                throw new NotFoundException("신청 대기중인 전공이 없습니다.");
            }

            // 응답 반환
            return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, LocalDateTime.now()));
    }




        // 전공 요청 목록 조회(교수)
    @Transactional
    public LmsResponse<List<AllMajorRes>> agreeMajorFindById(UUID id){
        List<Major> allMajorById = majorRepository.findAllMajorById(id);
        if(allMajorById.isEmpty()) {
            System.out.println("신청 대기 중인 전공 없습니다.");
        }

        List<AllMajorRes> resultList = new ArrayList<>();

        for (Major major : allMajorById) {
            resultList.add(new AllMajorRes(major));
        }

        return new LmsResponse<>(HttpStatus.ACCEPTED,resultList,null, LocalDateTime.now());
    }

    // 강의 요청 목록 조회(교수)
    @Transactional
    public LmsResponse<List<AllLectureRes>> agreeLectureFindById(UUID id){
        List<Lecture> allLectureById = lectureRepository.findAllLectureById(id);
        if(allLectureById.isEmpty()) {
            System.out.println("신청 대기 중인 강의가 없습니다."); //에러처리 해야됨
        }

        List<AllLectureRes> resultList = new ArrayList<>();

        for (Lecture lecture : allLectureById) {
            resultList.add(new AllLectureRes(lecture));
        }

        return new LmsResponse<>(HttpStatus.ACCEPTED,resultList,null, LocalDateTime.now());
    }



    //강의 등록 요청 취소(교수)
    @Transactional
    public void cancelLecture(ProfessorLectureRequest request){
        List<Lecture> allLectureById = lectureRepository.findAllLectureById(request.toEntity().getProfessor().getId());
        if(allLectureById.isEmpty()) {
            System.out.println("신청 대기중인 강의가 없습니다."); //에러처리 해야됨
        }
        lectureRepository.delete(request.toEntity());
    }


    // 강의 요청 목록 전체 조회(어드민)
    @Transactional
    public LmsResponse<List<AllLectureRes>> findAllLecture() {
        List<Lecture> allList = lectureRepository.findAllList();
        if(allList.isEmpty()){
            System.out.println("요청 대기중인 강의 목록이 없습니다."); //에러처리 필요함
        }

        List<AllLectureRes> resultList = new ArrayList<>();

        for (Lecture lecture : allList) {
            resultList.add(new AllLectureRes(lecture));
        }

        return new LmsResponse<>(HttpStatus.ACCEPTED,resultList,null,LocalDateTime.now());
    }

    //강의 요청 승인(어드민)
    @Transactional
    public void acceptLecture(UUID adminId) {
        List<Lecture> allList = lectureRepository.findAllList();

        if(allList.isEmpty() ||
                allList.stream().anyMatch(lecture -> lecture.getStatus().equals(Status.DENIED) || lecture.getStatus().equals(Status.ACCEPT))) {
            System.out.println("요청 대기중인 강의가 없거나 이미 처리된 강의가 있습니다"); // 에러 처리 필요함.
        }

        lectureRepository.updateLecture(adminId);
    }



    //강의 요청 거부(어드민)
    @Transactional
    public void denyLecture(AdminLectureRequest request){
        List<Lecture> allList = lectureRepository.findAllList();

        if(allList.isEmpty() ||
                allList.stream().anyMatch(lecture -> lecture.getStatus().equals(Status.DENIED) || lecture.getStatus().equals(Status.ACCEPT))) {
            System.out.println("요청 대기중인 강의가 없거나 이미 처리된 강의가 있습니다");
        }

        lectureRepository.save(request.toEntity());

    }


}
