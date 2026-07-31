package com.nisi.jobportalbackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data //It automatically generates all the boilerplate code that you would otherwise write manually:eg getter setters , toString(),equals(),hashCode()
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //@GeneratedValue tells the database to automatically generate the id value every time a new record is inserted.
    private Long id;
    private String jobRole;
    private String jobDescription;
    private String location;
    private Long annualIncome;
    private LocalDateTime deadline;
    private String perks;
    private String jobType;

    @ManyToOne
    @JoinColumn(name="recruiter_id")
    private User recruiter;

    private LocalDateTime createdAt;


//    This runs automatically right before JPA inserts the row — no manual code needed in your service layer.
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
