package com.team.lms.global.admin.repository;

import com.team.lms.global.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository
        extends JpaRepository<Admin, Long> {
}
