package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.repository.UserRepository;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.mapper.JobRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.JobStateReason;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
@Service
@Transactional

public class JobRegisterServiceImp implements JobRegisterService {
    @Autowired
    public JobRegisterRepository jobRegisterRepository;
    @Autowired
    JobRegisterMapper jobRegisterMapper;
    @Autowired
    public UserRepository userRepository;
    @Autowired


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
    public JobRegisterDTO save(JobRegisterDTO jobRegisterDTO) {
//        return jobRegisterRepository.save(jobRegisterDTO);
        return null;
    }


    @Override
    public void deleteById(Long id) {
        jobRegisterRepository.deleteById(id);
    }

    @Override
    public void updateById(JobRegisterDTO jobRegisterDTO, Long id) {

        JobRegister jobRegister = jobRegisterMapper.toEntity(jobRegisterDTO);
        jobRegister.setId(id);
        jobRegisterRepository.save(jobRegister);
    }
    public Page<JobRegisterDTO> findAlD(Pageable pageable){
        return (Page<JobRegisterDTO>) jobRegisterRepository.findAll((Sort) pageable).stream().map(jobRegisterMapper::toDto);
    }

    /*
     * trungnd
     */

    @Override
    public int countAll() {
        return jobRegisterRepository.countAll();
    }

    @Override
    public int countJobRegByStatus(Long statusId, String smallDate, String bigDate) {
        return jobRegisterRepository.countJobRegisterByStatus(statusId, smallDate,  bigDate);
    }
}
