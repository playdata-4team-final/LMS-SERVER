package com.example.lms.lecture.service;


import com.example.lms.domain.response.LmsResponse;
import com.example.lms.exception.ClientException;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.lecture.domain.request.AdminLectureRequest;
import com.example.lms.lecture.domain.request.AdminMajorRequest;
import com.example.lms.lecture.domain.request.ProfessorLectureRequest;
import com.example.lms.lecture.domain.request.ProfessorMajorRequest;
import com.example.lms.lecture.domain.response.AllLectureRes;
import com.example.lms.lecture.domain.response.AllMajorRes;
import com.example.lms.lecture.exception.DuplicateException;
import com.example.lms.lecture.exception.NotFoundException;
import com.example.lms.lecture.repository.LectureRepository;
import com.example.lms.major.entity.Major;
import com.example.lms.major.exception.MethodException;
import com.example.lms.major.repository.MajorRepository;
import com.example.lms.professor.entity.Professor;
import com.example.lms.professor.repository.ProfessorRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<LmsResponse<String>> requestLecture(ProfessorLectureRequest request) {
        String professorId = request.getProfessorId(); // UUID를 문자열로 받음

        Long majorId = request.getMajorId();
        Professor existingProfessor = professorRepository.findByProfessorId(professorId).orElse(null);
        Major existingMajor = majorRepository.findById(majorId).orElse(null);
        List<Lecture> allLectureById = lectureRepository.findAllLectureById(professorId);

        if (!allLectureById.isEmpty()) {
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
        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, null, null, null, LocalDateTime.now()));
    }


    //전공 요청(교수)
    @Transactional
    public ResponseEntity<LmsResponse<Object>> requestMajor(List<ProfessorMajorRequest> requests) {
        List<Major> majorList = new ArrayList<>();
        String professorId = requests.get(0).getProfessorId();
        List<Major> allMajorById = majorRepository.findAllMajorById(professorId);
        try {

            for (ProfessorMajorRequest request : requests) {
                Professor professor = professorRepository.findByProfessorId(professorId).orElse(null);

                if (professor == null) {
                    return ResponseEntity.ok()
                            .body(new LmsResponse<>(HttpStatus.NOT_FOUND, null, null, new NotFoundException("유저 정보가 없습니다.").getErrorMsg(), LocalDateTime.now()));
                } else if (allMajorById.size() != 0) {
                    return ResponseEntity.ok()
                            .body(new LmsResponse<>(HttpStatus.BAD_GATEWAY, null, null, new DuplicateException("이미 존재하는 전공입니다.").getErrorMsg(), LocalDateTime.now()));
                }

                // 전공 생성
                List<String> majorNames = request.getMajorNames();

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

            // 결과 데이터를 찾아오기
            List<Major> majorsForResult = majorRepository.findAllMajorById(professorId);
            majorList.addAll(majorsForResult);

            // 응답 반환
            return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, majorList, "성공", null, LocalDateTime.now()));
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(new LmsResponse<>(HttpStatus.BAD_REQUEST, null, e.getMessage(), "알 수 없는 에러 발생", LocalDateTime.now()));
        }
    }


    //전공 등록 요청 취소(교수)
    @Transactional
    public ResponseEntity<LmsResponse<String>> cancelMajor(List<ProfessorMajorRequest> requests) {
        try {
            for (ProfessorMajorRequest request : requests) {
                String professorId = request.getProfessorId();

                List<String> majorNames = request.getMajorNames();
                List<Major> allMajorById = majorRepository.findAllMajorById(professorId);
                if (!majorNames.isEmpty()) {
                    String majorName = majorNames.get(0);
                    if (allMajorById.size() != 0) {
                        // professorId와 majorName으로 전공 삭제
                        majorRepository.deleteByProfessorIdAndMajorName(professorId, majorName);
                    } else {
                        //데이터가 비었을 때 처리
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new LmsResponse<>(HttpStatus.NOT_FOUND, null, null, new NotFoundException("신청 대기중인 과목이 없습니다").getErrorMsg(), LocalDateTime.now()));
                    }
                } else {
                    //요청이 잘못됨
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new LmsResponse<>(HttpStatus.BAD_REQUEST, null, null, new ClientException("잘못된 요청입니다").getErrorMsg(), LocalDateTime.now()));
                }
            }
            // 모든 요청이 성공했을 때 응답 반환
            return ResponseEntity.ok(new LmsResponse<>(HttpStatus.OK, "", "에러없음", null, LocalDateTime.now()));
        } catch (Exception e) {
            // 모든 요청이 실패했을 때 도 OK를 던져야함 대신 ERROR가 뭔지 알수 있어야함.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new LmsResponse<>(HttpStatus.BAD_REQUEST, e.getMessage(), null, null, LocalDateTime.now()));
        }
    }


    // 전공 요청 목록 조회(교수)
    @Transactional
    public ResponseEntity<LmsResponse<List<AllMajorRes>>> approvedMajorFindById(String id) {
        try {
            List<Major> allMajorById = majorRepository.findApprovedMajorById(id);

            if (allMajorById.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new LmsResponse<>(HttpStatus.NOT_FOUND, null, null, new NotFoundException("승인대기 중인 과목이 없습니다.").getErrorMsg(), LocalDateTime.now()));
            }

            List<AllMajorRes> resultList = new ArrayList<>();

            for (Major major : allMajorById) {
                AllMajorRes allMajorRes = new AllMajorRes(major);
                resultList.add(allMajorRes);
            }

            return ResponseEntity.ok(new LmsResponse<>(HttpStatus.ACCEPTED, resultList, "에러없음", null, LocalDateTime.now()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new LmsResponse<>(HttpStatus.BAD_REQUEST, new ArrayList<>(), null, "알수 없는 에러 발생", LocalDateTime.now()));

        }
    }


    // 강의 요청 목록 조회(교수)
    @Transactional
    public LmsResponse<List<AllLectureRes>> agreeLectureFindById(String id) {
        List<Lecture> allLectureById = lectureRepository.findAllLectureById(id);
        if (allLectureById.isEmpty()) {
            System.out.println("신청 대기 중인 강의가 없습니다."); //에러처리 해야됨
        }

        List<AllLectureRes> resultList = new ArrayList<>();

        for (Lecture lecture : allLectureById) {
            resultList.add(new AllLectureRes(lecture));
        }

        return new LmsResponse<>(HttpStatus.ACCEPTED, resultList, null, null, LocalDateTime.now());
    }


    //강의 등록 요청 취소(교수)
    @Transactional
    public ResponseEntity<LmsResponse<String>> cancelLecture(ProfessorLectureRequest request) {
        try {
            List<Lecture> allLectureById = lectureRepository.findAllLectureById(request.toEntity().getProfessor().getId());
            if (allLectureById.isEmpty()) {
                throw new NotFoundException("요청 대기중인 강의가 없습니다.");
            }
            lectureRepository.delete(request.toEntity());
            return ResponseEntity.ok(new LmsResponse<>(HttpStatus.ACCEPTED, "", null, null, LocalDateTime.now()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new LmsResponse<>(HttpStatus.BAD_REQUEST, null, null, null, LocalDateTime.now()));
        }
    }


    // 강의 요청 목록 전체 조회(어드민)
    @Transactional
    public LmsResponse<List<AllLectureRes>> findAllLecture() {
        List<Lecture> allList = lectureRepository.findAllList();
        if (allList.isEmpty()) {
            System.out.println("요청 대기중인 강의 목록이 없습니다."); //에러처리 필요함
        }

        List<AllLectureRes> resultList = new ArrayList<>();

        for (Lecture lecture : allList) {
            resultList.add(new AllLectureRes(lecture));
        }

        return new LmsResponse<>(HttpStatus.ACCEPTED, resultList, null, null, LocalDateTime.now());
    }

    // 전공 목록 전체 조회(어드민)
    @Transactional
    public ResponseEntity<LmsResponse<List<AllMajorRes>>> findAllMajors() {
        List<Major> all = majorRepository.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new LmsResponse<>(HttpStatus.NOT_FOUND, null, null, null, LocalDateTime.now()));
        }

        List<AllMajorRes> resultList = new ArrayList<>();

        for (Major major : all) {
            resultList.add(new AllMajorRes(major));
        }

        return ResponseEntity.ok(new LmsResponse<>(HttpStatus.ACCEPTED, resultList, null, null, LocalDateTime.now()));
    }


    //강의 요청 승인(어드민)
    @Transactional
    public void acceptLecture(AdminLectureRequest request) {
        List<Lecture> allList = lectureRepository.findAllList();

        if (allList.isEmpty() ||
                allList.stream().anyMatch(lecture -> lecture.getStatus().equals(Status.DENIED) || lecture.getStatus().equals(Status.ACCEPT))) {
            System.out.println("요청 대기중인 강의가 없거나 이미 처리된 강의가 있습니다"); // 에러 처리 필요함.

        }

        lectureRepository.updateLecture(request.getLectureId(), request.getRoomNumber(), request.getWeekDay(), request.getStartTime(), request.getRoomCheck());
    }


    //강의 요청 거부(어드민)
    @Transactional
    public void denyLecture(AdminLectureRequest request) {
        List<Lecture> allList = lectureRepository.findAllList();

        if (allList.isEmpty() ||
                allList.stream().anyMatch(lecture -> lecture.getStatus().equals(Status.DENIED) || lecture.getStatus().equals(Status.ACCEPT))) {
            System.out.println("요청 대기중인 강의가 없거나 이미 처리된 강의가 있습니다");
        }

        lectureRepository.save(request.toEntity());

    }

    //전공 요청 승인(어드민)
    @Transactional
    public ResponseEntity<LmsResponse<String>> acceptMajor(AdminMajorRequest request) {
        try {
            Major majorById = majorRepository.findByMajorId(request.getMajorId());

            if (majorById == null ||
                    majorById.getStatus().equals(Status.DENIED) || majorById.getStatus().equals(Status.ACCEPT)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new LmsResponse<>(HttpStatus.NOT_FOUND, null, null, new NotFoundException("처리 되지 않은 전공 목록이 없습니다.").getErrorMsg(), LocalDateTime.now()));
            }

            Major major = majorRepository.updateAcceptMajor(request.getMajorId());
            if (majorById == major) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                        .body(new LmsResponse<>(HttpStatus.BAD_GATEWAY, null, null, new MethodException("알 수 없는 에러 발생").getErrorMsg(), LocalDateTime.now()));
            }

            return ResponseEntity.ok(new LmsResponse<>(HttpStatus.ACCEPTED, "", null, null, LocalDateTime.now()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new LmsResponse<>(HttpStatus.BAD_REQUEST, null, null, "알수 없는 에러 발생", LocalDateTime.now()));
        }
    }


    //전공 요청 거부(어드민)
    @Transactional
    public ResponseEntity<LmsResponse<String>> denyMajor(AdminMajorRequest request) {
        try {
            Major majorById = majorRepository.findByMajorId(request.getMajorId());

            if (majorById == null ||
                    majorById.getStatus().equals(Status.DENIED) || majorById.getStatus().equals(Status.ACCEPT)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new LmsResponse<>(HttpStatus.NOT_FOUND, null, null, new NotFoundException("처리 되지 않은 전공 목록이 없습니다.").getErrorMsg(), LocalDateTime.now()));
            }

            Major major = majorRepository.updateDenyMajor(request.getMajorId());
            if (majorById == major) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                        .body(new LmsResponse<>(HttpStatus.BAD_GATEWAY, null, null, new MethodException("알 수 없는 에러 발생").getErrorMsg(), LocalDateTime.now()));
            }

            return ResponseEntity.ok(new LmsResponse<>(HttpStatus.ACCEPTED, "", null, null, LocalDateTime.now()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new LmsResponse<>(HttpStatus.BAD_REQUEST, null, null, "알수 없는 에러 발생", LocalDateTime.now()));
        }
    }
}







