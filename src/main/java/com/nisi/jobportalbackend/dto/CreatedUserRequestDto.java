package com.nisi.jobportalbackend.dto;

import com.nisi.jobportalbackend.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreatedUserRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min=3 ,max = 50 , message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Role is required")
    private Role role;

    @NotBlank(message = "Password is required")
    @Size(min= 4 , max = 64, message = "Password must be between 6 to 64 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain uppercase,lowercase,number,and especial character"
    )
    private String password;
}
