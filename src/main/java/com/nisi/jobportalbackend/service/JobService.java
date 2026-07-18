package com.nisi.jobportalbackend.service;

import com.nisi.jobportalbackend.entity.Job;
import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.repository.JobRepo;
import com.nisi.jobportalbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private UserRepo userRepo;

//    SecurityContextHolder stores the logged in user set by JwtFilter on every request.
    // getName() internally calls getUsername() on UserDetails → returns email in our project.
    public Job postJob(Job job){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName(); // it will return email of logged in user
        System.out.println("Logged in user email: " + email);
        User recruiter = userRepo.findByEmail(email)           // it loads the full user from db
                .orElseThrow(()->new RuntimeException("User not found"));

//      then set the recruiter on the job before saving:
        job.setRecruiter(recruiter);
        return jobRepo.save(job);
    }

    public List<Job> getAllJobs(){
       return jobRepo.findAll();
    }

    public List<Job> getMyJobs(String email) {
        return jobRepo.findByRecruiter_Email(email);
    }
}
