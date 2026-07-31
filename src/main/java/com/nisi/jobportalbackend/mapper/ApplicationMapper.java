package com.nisi.jobportalbackend.mapper;

import com.nisi.jobportalbackend.dto.ApplicationResponseDto;
import com.nisi.jobportalbackend.entity.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationResponseDto toResponseDTO(Application application);
}
