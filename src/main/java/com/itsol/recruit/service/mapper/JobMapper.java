package com.itsol.recruit.service.mapper;


import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 */
@Service
public class JobMapper implements EntityMapper<JobDTO, Job> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RankRepository rankRepository;

    @Autowired
    WorkingFormRepository workingFormRepository;

    @Autowired
    AcademicLevelRepository academicLevelRepository;

    @Autowired
    JobPositionRepository jobPositionRepository;

    @Autowired
    StatusJobRepository statusJobRepository;

    @Override
    public Job toEntity(JobDTO dto) {
        if(dto == null){
            return null;
        }
        Job entity = new Job();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public JobDTO toDto(Job entity) {
        if(entity == null){
            return null;
        }
        JobDTO dto = new JobDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public List<Job> toEntity(List<JobDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }
    @Override
    public List<JobDTO> toDto(List<Job> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
