package com.nisi.jobportalbackend.repository;

import com.nisi.jobportalbackend.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepo extends JpaRepository<Recruiter,Long> {
}
