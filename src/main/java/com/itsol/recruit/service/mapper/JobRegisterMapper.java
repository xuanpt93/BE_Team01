package com.itsol.recruit.service.mapper;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.*;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.impl.JobServiceImpl;
import com.itsol.recruit.web.JobController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobRegisterMapper implements EntityMapper<JobRegisterDTO, JobRegister>{

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatusJobRegisterRepository statusJobRegister;
    @Autowired
    JobRepository jobRepository;

    @Override
    public JobRegister toEntity(JobRegisterDTO dto) {
        if(dto == null){
            return null;
        }
        JobRegister entity = new JobRegister();
        BeanUtils.copyProperties(dto, entity);
//        JobRegister jobRegister = new JobRegister();
//        jobRegister.setId(jobRegister.getId());
//        jobRegister.setUser(userRepository.getById(dto.getUserId()));
//        jobRegister.setJob(jobRepository.getById(dto.getJobRegisterId()));
//        jobRegister.setStatusJobRegister(statusJobRegister.getById(dto.getStatusJobRegisterId()));
        entity.setId(entity.getId());
        entity.setUser(userRepository.getById(dto.getUserId()));
        entity.setJob(jobRepository.getById(dto.getJobRegisterId()));
        entity.setStatusJobRegister(statusJobRegister.getById(dto.getStatusJobRegisterId()));
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
