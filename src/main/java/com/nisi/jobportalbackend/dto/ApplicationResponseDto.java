package com.nisi.jobportalbackend.dto;

import com.nisi.jobportalbackend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationResponseDto {

    private Long id;
    private String username;
    private String email;
    private Status status;
    private String message;
}
