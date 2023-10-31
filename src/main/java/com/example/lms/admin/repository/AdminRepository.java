package com.example.lms.admin.repository;

import com.example.lms.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository
        extends JpaRepository<Admin, String> {
}
