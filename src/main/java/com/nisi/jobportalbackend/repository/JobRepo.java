package com.nisi.jobportalbackend.repository;

import com.nisi.jobportalbackend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepo extends JpaRepository<Job,Long> {


    // Spring Data JPA automatically generates SQL from method names — no implementation needed!
    // JPA reads method name and translates it to SQL at runtime via a proxy class.
    // Underscore (_) tells JPA to navigate relationships:
    // findByRecruiter_Email → JOIN users ON jobs.recruiter_id = users.id WHERE users.email = ?
    // Without underscore → JPA looks for field "recruiterEmail" directly on Job → doesn't exist!
    List<Job> findByRecruiter_Email(String email);
}
