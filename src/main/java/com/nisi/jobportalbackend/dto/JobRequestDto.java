package com.nisi.jobportalbackend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobRequestDto {

    @NotBlank(message = "Job role is required")
    private String jobRole;
    @NotBlank(message = "Job description is required")
    private String jobDescription;
    @NotBlank(message = "Location is required")
    private String location;
    @Positive(message = "Annual Income must be postive")
    private Long annualIncome;
    @Future(message = "Deadline must be a future date")
    private LocalDateTime deadline;

    private String perks;
    @NotBlank(message = "Job type is required")
    private String jobType;

}
