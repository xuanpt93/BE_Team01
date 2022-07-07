package com.itsol.recruit.service.mapper;


import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 */
@Service
public class JobMapper implements EntityMapper<JobDTO, Job> {


    @Override
    public Job toEntity(JobDTO dto) {
        if(dto == null){
            return null;
        }
        Job enity = new Job();
        BeanUtils.copyProperties(dto,enity);
        return enity;
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
