package com.nisi.jobportalbackend.controller;

import com.nisi.jobportalbackend.dto.JobRequestDto;
import com.nisi.jobportalbackend.dto.JobResponseDto;
import com.nisi.jobportalbackend.entity.Job;
import com.nisi.jobportalbackend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("jobs")
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping()
//    @PreAuthorize("hasRole('RECRUITER')")
    public JobResponseDto postJob(@Valid @RequestBody JobRequestDto job){
        return jobService.postJob(job);
    }

    @GetMapping()
    public List<JobResponseDto> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/my")
    public List<JobResponseDto> getMyJobs(){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return jobService.getMyJobs(email);
    }
}
