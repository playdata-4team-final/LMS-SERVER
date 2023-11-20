package com.example.lms.major.entity;

import com.example.lms.lecture.domain.entity.Lecture;
import com.example.lms.lecture.domain.entity.Status;
import com.example.lms.professor.entity.Professor;
import com.example.lms.professor.entity.ProfessorMajor;
import com.example.lms.student.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Major {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean checkMajor;

    @Column(unique = true, nullable = false)//여기서 전공은 유일한 값을 가져야함.
    private String majorName;

    @Enumerated(EnumType.STRING)
    private Status status;
    

    @OneToMany(mappedBy = "major")
    private List<Lecture> lectures;

    @OneToMany(mappedBy = "major")
    private List<ProfessorMajor> majorList;

    public  void changeStatus(Status status){ this.status=status;}
    @Override
    public String toString() {
        return "Major{" +
                "id=" + id +
                ", checkMajor=" + checkMajor +
                ", majorName='" + majorName + '\'' +
                ", status=" + status +
                '}';
    }

}
