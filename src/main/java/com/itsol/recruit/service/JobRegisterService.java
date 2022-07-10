package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.JobRegister;

import java.util.List;
import java.util.Optional;

public interface JobRegisterService {
    List<JobRegister> getAllJobRegister();
    Optional<JobRegister> findById(Long id);
    JobRegister addJobRegister(JobRegisterDTO dto);
    void deleteById(Long id);
    void updateById(JobRegisterDTO jobRegisterDTO, Long id);

    JobRegisterDTO save(JobRegisterDTO jobRegisterDTO);
}
