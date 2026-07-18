package com.nisi.jobportalbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        name = "applications", // renames the table from default "application" to "applications"
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "job_id"}
                        // ensures the COMBINATION of user_id + job_id is unique
                        // i.e., same candidate CANNOT apply to the same job more than once
                        // enforced at the DATABASE level, not just in Java code
                )
        }
)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User candidate;

    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private LocalDateTime appliedAt;
}
