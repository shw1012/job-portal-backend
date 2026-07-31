package com.nisi.jobportalbackend.service;

import com.nisi.jobportalbackend.dto.JobRequestDto;
import com.nisi.jobportalbackend.dto.JobResponseDto;
import com.nisi.jobportalbackend.entity.Job;
import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.exception.ResourceNotFoundException;
import com.nisi.jobportalbackend.mapper.JobMapper;
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

    @Autowired
    private JobMapper jobMapper;

//    SecurityContextHolder stores the logged in user set by JwtFilter on every request.
    // getName() internally calls getUsername() on UserDetails → returns email in our project.
    public JobResponseDto postJob(JobRequestDto jobRequestDto){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName(); // it will return email of logged in user
//        System.out.println("Logged in user email: " + email);
        User recruiter = userRepo.findByEmail(email)         // it loads the full user from db
                .orElseThrow(()->new ResourceNotFoundException("User with this email "+email+" not found"));

//      then set the recruiter on the job before saving:
        Job job = jobMapper.toEntity(jobRequestDto); //dto to entity conversion.
        job.setRecruiter(recruiter);
        jobRepo.save(job);
        return jobMapper.toResponseDTO(job);
    }

    public List<JobResponseDto> getAllJobs(){

        List<Job> jobs= jobRepo.findAll();

         return jobs.stream()
                 .map(job->jobMapper.toResponseDTO(job))
                 .toList();
    }

    public List<JobResponseDto> getMyJobs(String email) {

        List<Job> jobs=jobRepo.findByRecruiter_Email(email);
        return jobs.stream().
                map(job->jobMapper.toResponseDTO(job))
                .toList();
    }
}
