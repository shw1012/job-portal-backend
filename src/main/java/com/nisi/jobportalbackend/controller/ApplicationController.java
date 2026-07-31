package com.nisi.jobportalbackend.controller;

import com.nisi.jobportalbackend.dto.ApplicationResponseDto;
import com.nisi.jobportalbackend.entity.Application;
import com.nisi.jobportalbackend.entity.Status;
import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.repository.ApplicationRepo;
import com.nisi.jobportalbackend.repository.UserRepo;
import com.nisi.jobportalbackend.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RequestMapping("application")
@RestController
public class ApplicationController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply/{jobId}")
    public ApplicationResponseDto apply(@Valid @PathVariable Long jobId){

        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User candidate= userRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found"));

        return applicationService.apply(candidate.getId(),jobId);
    }

    @GetMapping
    public List<ApplicationResponseDto> getApplicationByCandidate(){
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        Long candidateId = userRepo.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User not found")).getId();

        return applicationService.getApplicationByCandidate(candidateId);

    }

    @GetMapping("{jobId}")
    public List<ApplicationResponseDto> getApplicationByJob(@PathVariable Long jobId) throws AccessDeniedException {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        // email = "candidate@gmail.com" — the actual logged-in user, from their JWT
        return applicationService.getApplicationByJob(jobId,email);

    }

    @PutMapping("/update/{applicationId}/{status}")
    public ApplicationResponseDto updateApplication(@PathVariable Long applicationId, @PathVariable Status status) throws AccessDeniedException {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        return applicationService.updateApplication(applicationId,status,email);
    }

}
