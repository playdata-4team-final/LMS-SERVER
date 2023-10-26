package com.team.lms.lecture.service;

import com.team.lms.global.domain.request.LmsRequest;
import com.team.lms.global.domain.response.LmsResponse;
import com.team.lms.lecture.domain.entity.Lecture;
import com.team.lms.lecture.domain.entity.Status;
import com.team.lms.lecture.domain.request.AdminLectureRequest;
import com.team.lms.lecture.domain.request.ProfessorLectureRequest;
import com.team.lms.lecture.domain.response.AllLectureRes;
import com.team.lms.lecture.exception.LectureFailException;
import com.team.lms.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LectureService {

    @Autowired
    private final LectureRepository lectureRepository;

    //강의 등록 요청(교수)
    @Transactional
    public void requestLecture(LmsRequest<ProfessorLectureRequest> request){
        Optional<Lecture> byId = lectureRepository.findById(request.getRequest().toEntity().getId());
        if(byId.isEmpty()) {
            lectureRepository.save(request.getRequest().toEntity());
        } else new LectureFailException("이미 신청한 강의입니다.");
    }

    // 강의 요청 목록 조회(교수)
    @Transactional
    public LmsResponse<List<AllLectureRes>> agreeLectureFindById(UUID id){
        List<Lecture> allLectureById = lectureRepository.findAllLectureById(id);
        if(allLectureById.isEmpty()) {
            throw new LectureFailException("신청 대기 중인 강의가 없습니다.");
        }

        List<AllLectureRes> resultList = new ArrayList<>();

        for (Lecture lecture : allLectureById) {
            resultList.add(new AllLectureRes(lecture));
        }

        return new LmsResponse<>(true,resultList,null);
    }



    //강의 등록 요청 취소(교수)
    @Transactional
    public void cancelLecture(LmsRequest<ProfessorLectureRequest> request){
        List<Lecture> allLectureById = lectureRepository.findAllLectureById(request.getRequest().toEntity().getProfessor().getId());
        if(allLectureById.isEmpty()) {
            throw new LectureFailException("신청 대기중인 강의가 없습니다.");
        }
        lectureRepository.delete(request.getRequest().toEntity());
    }


    // 강의 요청 목록 전체 조회(어드민)
    @Transactional
    public LmsResponse<List<AllLectureRes>> findAllLecture() {
        List<Lecture> allList = lectureRepository.findAllList();
        if(allList.isEmpty()){
            throw new LectureFailException("요청 대기중인 강의 목록이 없습니다.");
        }

        List<AllLectureRes> resultList = new ArrayList<>();

        for (Lecture lecture : allList) {
            resultList.add(new AllLectureRes(lecture));
        }

        return new LmsResponse<>(true,resultList,null);
    }

    //강의 요청 승인(어드민)
    @Transactional
    public void acceptLecture(UUID adminId) {
        List<Lecture> allList = lectureRepository.findAllList();

        if(allList.isEmpty() ||
                allList.stream().anyMatch(lecture -> lecture.getStatus().equals(Status.DENIED) || lecture.getStatus().equals(Status.ACCEPT))) {
            throw new LectureFailException("요청 대기중인 강의가 없거나 이미 처리된 강의가 있습니다");
        }

        lectureRepository.updateLecture(adminId);
    }



    //강의 요청 거부(어드민)
    @Transactional
    public void denyLecture(LmsRequest<AdminLectureRequest> request){
        List<Lecture> allList = lectureRepository.findAllList();

        if(allList.isEmpty() ||
                allList.stream().anyMatch(lecture -> lecture.getStatus().equals(Status.DENIED) || lecture.getStatus().equals(Status.ACCEPT))) {
            throw new LectureFailException("요청 대기중인 강의가 없거나 이미 처리된 강의가 있습니다");
        }

        lectureRepository.save(request.getRequest().toEntity());
    }


}
