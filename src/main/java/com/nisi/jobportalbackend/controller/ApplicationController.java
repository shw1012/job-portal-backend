package com.nisi.jobportalbackend.controller;

import com.nisi.jobportalbackend.entity.Application;
import com.nisi.jobportalbackend.entity.Status;
import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.repository.ApplicationRepo;
import com.nisi.jobportalbackend.repository.UserRepo;
import com.nisi.jobportalbackend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("application")
@RestController
public class ApplicationController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepo applicationRepo;

    @PostMapping("/apply/{jobId}")
    public Application apply(@PathVariable Long jobId){

        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User candidate= userRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));

        return applicationService.apply(candidate.getId(),jobId);
    }

    @GetMapping
    public List<Application> getApplicationByCandidate(){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Long candidateId = userRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found")).getId();

        return applicationService.getApplicationByCandidate(candidateId);

    }

    @GetMapping("{jobId}")
    public List<Application> getApplicationByJob(@PathVariable Long jobId){
        return applicationService.getApplicationByJob(jobId);

    }

    @PutMapping("/update/{applicationId}/{status}")
    public Application updateApplication(@PathVariable Long applicationId, @PathVariable Status status){
        return applicationService.updateApplication(applicationId,status);
    }

}
