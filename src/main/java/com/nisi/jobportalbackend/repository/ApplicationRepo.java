package com.nisi.jobportalbackend.repository;

import com.nisi.jobportalbackend.entity.Application;
import com.nisi.jobportalbackend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application,Long> {
 // Spring data JPA naming convention :(findBy<Field>_<NestedField>, existsBy<FieldName>, etc.)
    //by this naming convention we dont needy to write any SQl.
    // Spring jpa will do automatically.


//    This tells Spring Data JPA: "go into the candidate field (which is a User object),
//    then look at its id field, and match against the parameter."
    List<Application> findByCandidate_id(Long id);

    List<Application> findByJob_id(Long id);

    boolean existsByCandidate_IdAndJob_Id(Long candidateId ,Long jobId);
}
