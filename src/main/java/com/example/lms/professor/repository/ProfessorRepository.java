package com.example.lms.professor.repository;

import com.example.lms.professor.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfessorRepository
        extends JpaRepository<Professor, String> {
    @Query("select p from Professor as p where  p.id = :professorId")
    public Optional<Professor> findByProfessorId(@Param("professorId") String professorId);
}
