package com.example.lms.lecture.service;

import com.example.lms.global.exception.ClientException;
import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.lecture.domain.request.*;
import com.example.lms.lecture.domain.response.AllLectureRes;
import com.example.lms.lecture.domain.response.AllMajorRes;
import com.example.lms.lecture.dto.AllLectureDto;
import com.example.lms.lecture.dto.AllMajorDto;
import com.example.lms.global.exception.DuplicateException;
import com.example.lms.global.exception.NotFoundException;
import com.example.lms.lecture.repository.LectureRepository;
import com.example.lms.major.entity.Major;
import com.example.lms.global.exception.MethodException;
import com.example.lms.major.repository.MajorRepository;
import com.example.lms.professor.dto.ProfessorDto;
import com.example.lms.professor.repository.ProfessorRepository;
import com.example.lms.room.entity.Room;
import com.example.lms.room.repository.RoomRepository;
import com.example.lms.schedule.entity.Schedule;
import com.example.lms.schedule.entity.WeekDay;
import com.example.lms.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {

    @Autowired
    private final LectureRepository lectureRepository;

    @Autowired
    private final ScheduleRepository scheduleRepository;

    @Autowired
    private final RoomRepository roomRepository;

    @Autowired
    private final MajorRepository majorRepository;

    @Autowired
    private final ProfessorRepository professorRepository;

    //강의 등록 요청(교수)
    @Transactional
    public Lecture requestLecture(ProfessorLectureRequest request) {
        String professorId = request.getProfessorId();
        Long majorId = request.getMajorId();
        try {
            ProfessorDto byProfessorId = professorRepository.findByProfessorId(professorId);
            Major existingMajor = majorRepository.findById(majorId).orElse(null);

            if (byProfessorId == null || existingMajor == null) {
                throw new NotFoundException("전공이나 등록된 교수 정보가 없습니다.");
            }

            //toEntity로 저장
            Lecture save = lectureRepository.save(request.toEntity());

            return save;
        } catch (Exception e) {
            e.printStackTrace();
           throw new RuntimeException("강의 저장 실패");
        }
    }





    //전공 요청(교수)
    @Transactional
    public String requestMajor(ProfessorMajorRequest request) {
                String professorId = request.getProfessorId();
                String majorName = request.getMajorName();
                AllMajorDto allMajorById = majorRepository.findByProfessorIdAndMajorName(professorId,majorName);

                if (professorId == null) {
                    throw new NotFoundException("유저 정보가 없습니다.");
                } else if (allMajorById != null) {
                    throw new DuplicateException("이미 존재하는 전공입니다.");
                }
                // 전공 생성
                Major save = majorRepository.save(request.toEntity());
                if (save == null){
                 return "Failed RequestMajor";
                }
            // 응답 반환
            return  "Success RequestMajor!";
    }


    //전공 등록 요청 취소(교수)
    @Transactional
    public String cancelMajor(ProfessorMajorCancelRequest request) {
        try {
            if (request.getMajorIds() != null) {
                // major 조회
                List<AllMajorDto> byIdQuery = majorRepository.findByIdQuery(request.getMajorIds());

                if (!byIdQuery.isEmpty()) {

                    // professor와 연관 관계 모두 해제
                    for (AllMajorDto majorDto : byIdQuery) {
                        deleteMajorForeignKey(majorDto.getId());
                    }

                    // major 삭제
                    majorRepository.deleteByMajorId(request.getMajorIds());

                } else {
                    // major가 없을 때
                    throw new NotFoundException("전공이 없습니다.");
                }
            } else {
                // request 오류
                throw new ClientException("잘못된 요청입니다.");
            }

            // 모든 요청이 성공했을 때 응답 반환
            return "Cancel Success";
        } catch (Exception e) {
            // 모든 요청이 실패했을 때 도 OK를 던져야 함 대신 ERROR가 뭔지 알 수 있어야 함.
            throw new RuntimeException(e.getMessage());
        }
    }




    // 전공 요청 목록 조회(교수)
    @Transactional
    public List<AllMajorRes> approvedMajorFindById(String id) {
            List<AllMajorDto> approvedMajorById = majorRepository.findApprovedMajorById(id);
            if (approvedMajorById.isEmpty()) {
                throw new NotFoundException("승인대기 중인 과목이 없습니다.");
            }
            List<AllMajorRes> resultList = new ArrayList<>();

            for (AllMajorDto major : approvedMajorById) {
                AllMajorRes allMajorRes = new AllMajorRes(major);
                resultList.add(allMajorRes);
            }

            return resultList;
        }


    // 강의 요청 목록 조회(교수)
    @Transactional
    public List<AllLectureRes> agreeLectureFindById(String id) {
            List<AllLectureDto> allLectureDTOsByProfessorId = lectureRepository.findAllLectureDTOsByProfessorId(id);
            if (allLectureDTOsByProfessorId.isEmpty()) {
                throw new NotFoundException("승인대기 중인 과목이 없습니다.");
            }
            List<AllLectureRes> resultList = new ArrayList<>();
        
            for (AllLectureDto lecture : allLectureDTOsByProfessorId) {
                AllLectureRes allLectureRes = new AllLectureRes(lecture);
                resultList.add(allLectureRes);
            } //stream 으로 나중에 변경 가능
            
            return resultList;
        }


    //강의 등록 요청 취소(교수)
    @Transactional
    public String cancelLecture(ProfessorLectureCancelRequest request) {
        try {
            if (request == null) {
                throw new ClientException("잘못된 요청입니다.");
            }
            lectureRepository.deleteByLectureId(request.getLectureIds());
            return "Cancel Success!";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    // 강의 요청 목록 전체 조회(어드민)
    @Transactional
    public List<AllLectureRes> findAllHoldingLecture() {
            List<AllLectureDto> allList = lectureRepository.findAllHoldingList();

            if (allList.isEmpty()) {
               throw new NotFoundException("빈 리스트입니다.");
            }

            List<AllLectureRes> resultList = new ArrayList<>();

            for (AllLectureDto lecture : allList) {
                resultList.add(new AllLectureRes(lecture));
            }

            return resultList;
    }

    // 강의 요청 목록 전체 조회(교수, 학생)
    @Transactional
    public List<AllLectureRes> findAllAcceptLecture() {
        List<AllLectureDto> allList = lectureRepository.findAllAcceptList();

        if (allList.isEmpty()) {
            throw new NotFoundException("빈 리스트입니다.");
        }

        List<AllLectureRes> resultList = new ArrayList<>();

        for (AllLectureDto lecture : allList) {
            resultList.add(new AllLectureRes(lecture));
        }

        return resultList;
    }


    //강의 요청 거부(어드민)
    @Transactional // 수정 필요함
    public String denyLecture(AdminLectureRequest request) {
        try {
            Optional<Lecture> byId = lectureRepository.findById(request.toEntity().getId());

            if (byId.get() ==null ||
                    byId.get().getStatus().equals(Status.DENIED) || byId.get().getStatus().equals(Status.ACCEPT)) {
               throw new NotFoundException("강의가 이미 처리 되었습니다.");
            }
            int check = changeDenyLecture(request.getLecture().getId());
            if (check == 0) {
               throw new MethodException("알 수 없는 에러 발생");
            }

           return  "Deny Success!";
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }

    // 전공 목록 전체 조회(어드민)
    @Transactional
    public List<AllMajorRes> findAllMajors() {
            List<AllMajorDto> all = majorRepository.findAllByStatus();
            if (all.isEmpty()) {
               throw new NotFoundException("빈 리스트입니다.");
            }

            List<AllMajorRes> resultList = new ArrayList<>();

            for (AllMajorDto major : all) {
                resultList.add(new AllMajorRes(major));
            }
            return resultList;
    }




    //전공 요청 승인(어드민)
    @Transactional
    public String acceptMajor(AdminMajorRequest request) {
        try {
            AllMajorDto byMajorIdandStatus = majorRepository.findByMajorIdandStatus(request.getMajorId());

            if (byMajorIdandStatus == null ||
                    byMajorIdandStatus.getStatus().equals(Status.DENIED) || byMajorIdandStatus.getStatus().equals(Status.ACCEPT)) {
               throw new NotFoundException("처리 되지 않은 전공 목록이 없습니다.");
            }

            int check = changeAcceptMajor(request.toEntity().getId());
            if (check == 0) {
                throw new MethodException("서비스 실패");
            }

            return "Accept Success";
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    //전공 요청 거부(어드민)
    @Transactional
    public String denyMajor(AdminMajorRequest request) {
        try {
            AllMajorDto byMajorIdandStatus = majorRepository.findByMajorIdandStatus(request.getMajorId());

            if (byMajorIdandStatus == null ||
                    byMajorIdandStatus.getStatus().equals(Status.DENIED) || byMajorIdandStatus.getStatus().equals(Status.ACCEPT)) {
               throw new NotFoundException("처리 되지 않은 전공 목록이 없습니다.");
            }
            int check = changeDenyMajor(request.getMajorId());
            if (check == 0) {
                throw new MethodException("전공 요청 거부 실패");
            }

            return "Deny Success!";
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    //강의 요청 승인(어드민)
    @Transactional
    public String acceptLecture(AdminLectureRequest request) {
        try {
            Optional<Lecture> byId = lectureRepository.findById(request.toEntity().getId());
            Lecture lecture = byId.get();
            if (lecture == null ||
                    lecture.getStatus().equals(Status.DENIED) || lecture.getStatus().equals(Status.ACCEPT)) {
                throw new NotFoundException("처리되지 않은 강의 목록이 없습니다.");
            }

            int check = changeAcceptLecture(request);
            if (check == 0) {
                throw new MethodException("강의 승인 실패");
            }

            return "Accept Success";
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    @Transactional
    public int changeAcceptLecture(AdminLectureRequest request) {
        try {
            Room room = roomRepository.findByRoomNumber(request.getRoomNumber()).orElseThrow(() -> new NotFoundException("Room not found"));
            Lecture lecture = request.getLecture();
            WeekDay weekDay = request.getWeekDay();
            Schedule schedule = scheduleRepository.findById(weekDay.getId()).orElseThrow(() -> new NotFoundException("Schedule not found"));

            room.changeRoomCheck(request.getRoomCheck());
            room.changeSchedule(schedule);

            // You can set other fields for WeekDay if needed

            Schedule newSchedule = Schedule.builder()
                    .weekdays(Collections.singletonList(weekDay))
                    .year(request.getLecture().getYear())  // Set your year logic here
                    .lectures(Collections.singletonList(lecture))
                    .roomId(room.getId())
                    .build();

            lecture.changeStatus(Status.ACCEPT);
            lecture.changeAcceptedAt(LocalDateTime.now());
            lecture.changeRoom(room);

            return 1;
        } catch (Exception e) {
            return 0;
        }
    }







    @Transactional
    public int changeAcceptMajor(Long majorId) {
        try{
            Major major = majorRepository.findById(majorId).get();
            major.changeStatus(Status.ACCEPT);

            return 1;
        }catch (Exception e) {
            return 0;
        }
    }

    @Transactional
    public int changeDenyLecture(Long lectureId) {
        try{

            Lecture lecture = lectureRepository.findById(lectureId).get();
            lecture.changeStatus(Status.DENIED);
            lecture.changeAcceptedAt(LocalDateTime.now());

            return 1;
        }catch (Exception e) {
            return 0;
        }
    }


    @Transactional
    public int changeDenyMajor(Long majorId) {
        try{

            Major major = majorRepository.findById(majorId).get();
            major.changeStatus(Status.DENIED);

            return 1;
        }catch (Exception e) {
            return 0;
        }
    }

    @Transactional
    public  int deleteMajorForeignKey(Long majorId){
        try{
            AllMajorDto byMajorIdandStatus = majorRepository.findByMajorIdandStatus(majorId);
            byMajorIdandStatus.setProfessorId(null);

            return 1;
        }catch (Exception e) {
            return 0;
        }
    }


}







