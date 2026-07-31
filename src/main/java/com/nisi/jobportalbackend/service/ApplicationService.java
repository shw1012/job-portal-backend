package com.nisi.jobportalbackend.service;

import com.nisi.jobportalbackend.dto.ApplicationResponseDto;
import com.nisi.jobportalbackend.entity.Application;
import com.nisi.jobportalbackend.entity.Job;
import com.nisi.jobportalbackend.entity.Status;
import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.exception.DuplicateResourceException;
import com.nisi.jobportalbackend.exception.ResourceNotFoundException;
import com.nisi.jobportalbackend.mapper.ApplicationMapper;
import com.nisi.jobportalbackend.repository.ApplicationRepo;
import com.nisi.jobportalbackend.repository.JobRepo;
import com.nisi.jobportalbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService{

    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ApplicationMapper applicationMapper;

    public ApplicationResponseDto apply(Long candidateId, Long jobId){

        //1.fetch candidate
        User candidate=userRepo.findById(candidateId)
                .orElseThrow(()->new ResourceNotFoundException("Candidate with id "+ candidateId +" not found."));
        //2.fetch job
        Job job =jobRepo.findById(jobId)
                .orElseThrow(()->new ResourceNotFoundException("Job with id " + jobId +" not found."));

        //3.check duplicate application
        if(applicationRepo.existsByCandidate_IdAndJob_Id(candidateId,jobId)){
            throw new DuplicateResourceException("You have already applied for this job");
        }
        //build new application object
        Application application= new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(Status.APPLIED);
        application.setAppliedAt(LocalDateTime.now()); //current timestamp

        //save and return
        applicationRepo.save(application);
        return applicationMapper.toResponseDTO(application);

    }
    public List<ApplicationResponseDto> getApplicationByCandidate(Long candidateId){

         List<Application> applications=applicationRepo.findByCandidate_id(candidateId);

      return applications.stream()
                .map(application -> applicationMapper.toResponseDTO(application))
                .toList();

    }
    public List<ApplicationResponseDto> getApplicationByJob(Long jobId,String requesterEmail){

        Job job = jobRepo.findById(jobId)
                .orElseThrow(()->new ResourceNotFoundException("Job with id " + jobId +" not found"));

        if(!job.getRecruiter().getEmail().equals(requesterEmail)){
            throw new AccessDeniedException("You don't have permission to view these applications");
        }

        List<Application> applications=  applicationRepo.findByJob_id(jobId);

        return applications.stream()
                .map(application -> applicationMapper.toResponseDTO(application))
                .toList();
    }

    public ApplicationResponseDto updateApplication(Long applicationId, Status status,String requesterEmail){

       //fetch application
       Application application= applicationRepo.findById(applicationId)
                       .orElseThrow(()->new ResourceNotFoundException("Application with id " + applicationId + " not found"));

       if(!application.getJob().getRecruiter().getEmail().equals(requesterEmail)){
           throw new AccessDeniedException("You don't have permission to update this application");
       }

       //update application status
       application.setStatus(status);

       //save application
        applicationRepo.save(application);

        return applicationMapper.toResponseDTO(application);

    }
}
