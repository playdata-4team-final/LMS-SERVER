package com.example.lms.mylecture.service;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.dto.AllLectureDto;
import com.example.lms.lecture.repository.LectureRepository;
import com.example.lms.mylecture.domain.request.MyScheduleRequest;
import com.example.lms.mylecture.repository.MyLectureRepository;
import com.example.lms.mylecture.domain.entity.MyLecture;
import com.example.lms.mylecture.domain.request.MyLectureRequest;
import com.example.lms.professor.entity.Professor;
import com.example.lms.room.entity.Room;
import com.example.lms.room.repository.RoomRepository;
import com.example.lms.schedule.entity.Schedule;
import com.example.lms.schedule.entity.WeekDay;
import com.example.lms.schedule.repository.ScheduleRepository;
import com.example.lms.student.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyLectureService {


    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MyLectureRepository myLectureRepository;



    @Transactional
    public List<MyLecture> getMyLecturesByStudent(String studentId) {
        return myLectureRepository.findByStudentId(studentId);
    }
    @Transactional
    public List<MyLecture> getMyLecturesByProfessor(String professorId) {
        return myLectureRepository.findByProfessorId(professorId);
    }
    @Transactional
    public List<MyLecture> getMyLecturesByLecture(Long lectureId){
        return myLectureRepository.findByLectureId(lectureId);
    }
    @Transactional
    public MyLecture createMyLecture(MyLectureRequest request) {

        // 요청을 MyLecture 엔티티로 변환하여 저장
        MyLecture myLecture = request.toEntity();
        return myLectureRepository.save(myLecture);
    }



    @Transactional
    public String saveWeeklyData(MyScheduleRequest scheduleRequest) {
            Schedule entities = scheduleRequest.toEntities();
        Schedule schedule = scheduleRepository.findLectureBySchedule(entities.getMemberId(), entities.getYear(), entities.getSemester()).get();
        scheduleRepository.save(schedule);
        return "Success Save!";
    }

}



