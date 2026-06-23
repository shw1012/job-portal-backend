package com.nisi.jobportalbackend.repository;

import com.nisi.jobportalbackend.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<Candidate,Long> {
}
