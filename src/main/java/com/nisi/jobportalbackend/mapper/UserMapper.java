package com.nisi.jobportalbackend.mapper;

import com.nisi.jobportalbackend.dto.CreatedUserRequestDto;
import com.nisi.jobportalbackend.dto.CreatedUserResponseDto;
import com.nisi.jobportalbackend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(CreatedUserRequestDto request);

    CreatedUserResponseDto toResponseDTO(User user);

}
