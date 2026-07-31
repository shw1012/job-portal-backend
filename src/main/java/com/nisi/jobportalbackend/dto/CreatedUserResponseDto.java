package com.nisi.jobportalbackend.dto;

import com.nisi.jobportalbackend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedUserResponseDto {

    private Long id;
    private String name;
    private String email;
    private Role role;
}
