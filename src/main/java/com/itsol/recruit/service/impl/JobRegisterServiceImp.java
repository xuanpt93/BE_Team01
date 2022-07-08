package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.mapper.JobRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.JobStateReason;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional

public class JobRegisterServiceImp implements JobRegisterService {
    @Autowired
    public JobRegisterRepository jobRegisterRepository;
    @Autowired
    JobRegisterMapper jobRegisterMapper;

    @Override
    public List<JobRegister> getAllJobRegister() {
        return jobRegisterRepository.findAllOrderByDateAsc();
    }

    @Override
    public Optional<JobRegister> findById(Long id) {
        return jobRegisterRepository.findById(id);
    }

    @Override
    public JobRegister addJobRegister(JobRegisterDTO dto) {
        JobRegister jobRegister = jobRegisterMapper.toEntity(dto);
        return jobRegisterRepository.save(jobRegister);
    }

    @Override
    public void deleteById(Long id) {
        jobRegisterRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id) {

    }
}
