package com.nisi.jobportalbackend.service;

import com.nisi.jobportalbackend.entity.Application;
import com.nisi.jobportalbackend.entity.Job;
import com.nisi.jobportalbackend.entity.Status;
import com.nisi.jobportalbackend.entity.User;
import com.nisi.jobportalbackend.repository.ApplicationRepo;
import com.nisi.jobportalbackend.repository.JobRepo;
import com.nisi.jobportalbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private UserRepo userRepo;

    public Application apply(Long candidateId,Long jobId){

        //1.fetch candidate
        User candidate=userRepo.findById(candidateId)
                .orElseThrow(()->new RuntimeException("Candidate not found"));
        //2.fetch job
        Job job =jobRepo.findById(jobId)
                .orElseThrow(()->new RuntimeException("Job not found"));

        //3.check duplicate application
        if(applicationRepo.existsByCandidate_IdAndJob_Id(candidateId,jobId)){
            throw new RuntimeException("You have already applied for this job");
        }
        //build new application object
        Application application= new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(Status.APPLIED);
        application.setAppliedAt(LocalDateTime.now()); //current timestamp

        //save and return
        return applicationRepo.save(application);
    }
    public List<Application> getApplicationByCandidate(Long candidateId){
        return applicationRepo.findByCandidate_id(candidateId);
    }
    public List<Application> getApplicationByJob(Long jobId){
        return applicationRepo.findByJob_id(jobId);
    }

    public Application updateApplication(Long applicationId, Status status){

       //fetch application
       Application application= applicationRepo.findById(applicationId)
                       .orElseThrow(()->new RuntimeException("Application not found"));
       //update application status
       application.setStatus(status);

       //save application
       return applicationRepo.save(application);

    }


}
