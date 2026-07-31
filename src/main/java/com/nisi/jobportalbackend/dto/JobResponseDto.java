package com.nisi.jobportalbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobResponseDto {

    private Long id;
    private String jobRole;
    private String jobDescription;
    private String location;
    private Long annualIncome;
    private LocalDateTime deadline;
    private String perks;
    private String jobType;
    private String recruiterName;
    private String recruiterEmail;
    private LocalDateTime createdAt;

}
