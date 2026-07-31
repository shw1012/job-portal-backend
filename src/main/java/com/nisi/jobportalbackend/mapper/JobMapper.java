package com.nisi.jobportalbackend.mapper;

import com.nisi.jobportalbackend.dto.JobRequestDto;
import com.nisi.jobportalbackend.dto.JobResponseDto;
import com.nisi.jobportalbackend.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobMapper {

    @Mapping(source = "recruiter.name", target = "recruiterName")
    @Mapping(source = "recruiter.email",target = "recruiterEmail")
    JobResponseDto toResponseDTO(Job job);

    Job toEntity(JobRequestDto jobRequestDto);
}
