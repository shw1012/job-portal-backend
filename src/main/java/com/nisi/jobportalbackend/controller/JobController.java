package com.nisi.jobportalbackend.controller;

import com.nisi.jobportalbackend.entity.Job;
import com.nisi.jobportalbackend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("jobs")
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping()
    public Job postJob( @RequestBody Job job){
        return jobService.postJob(job);
    }

    @GetMapping()
    public List<Job> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/my")
    public List<Job> getMyJobs(){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return jobService.getMyJobs(email);
    }
}
