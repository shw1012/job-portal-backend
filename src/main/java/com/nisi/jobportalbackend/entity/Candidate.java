package com.nisi.jobportalbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Candidate {

    @OneToOne(cascade = CascadeType.ALL) // it means whatever operation we are doing on user it apply on candidate also.
    @JoinColumn(name = "user_id") //tells Hibernate "create a column called user_id in the Candidate table, and store the connected User's ID there"
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String skills;
    String resumeLink;
}
