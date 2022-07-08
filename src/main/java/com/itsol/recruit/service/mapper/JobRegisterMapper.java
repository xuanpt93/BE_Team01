package com.itsol.recruit.service.mapper;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.JobRegister;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRegisterMapper implements EntityMapper<JobRegisterDTO, JobRegister>{


    @Override
    public JobRegister toEntity(JobRegisterDTO dto) {
        if(dto == null){
            return null;
        }
        JobRegister entity = new JobRegister();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public JobRegisterDTO toDto(JobRegister entity) {
        if(entity == null){
            return null;
        }
        JobRegisterDTO dto = new JobRegisterDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public List<JobRegister> toEntity(List<JobRegisterDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<JobRegisterDTO> toDto(List<JobRegister> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
