package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.dto.StatusJobRegisterDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.StatusJobRegister;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.JobRegisterRepository;
import com.itsol.recruit.repository.StatusJobRegisterRepository;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.mapper.JobRegisterMapper;
import com.itsol.recruit.specification.JobRegisterSpecification;
import com.itsol.recruit.specification.UserSpecification;
import com.itsol.recruit.web.vm.PageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional

public class JobRegisterServiceImp implements JobRegisterService {

    @Autowired
    public JobRegisterMapper jobRegisterMapper;
    @Autowired
    public JobRegisterRepository jobRegisterRepository;
    @Autowired
    public StatusJobRegisterRepository statusJobRegisterRepository;

    @Override
    public  Page<JobRegisterDTO> getAllJobRegister(PageVM pageVM, String search, String sortBy) {
        Pageable firstPageWithTwoElements;
        if(sortBy == null){
            firstPageWithTwoElements = PageRequest.of(pageVM.getPageNumber(), pageVM.getPageSize());
        }else {
            firstPageWithTwoElements = PageRequest.of(pageVM.getPageNumber(), pageVM.getPageSize(), Sort.by(sortBy));
        }
        Specification<JobRegister> where = JobRegisterSpecification.buildWhere(search);
        return  jobRegisterRepository.findAllOrderByDateAsc(firstPageWithTwoElements, where).map(jobRegisterMapper::toDto);

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
    public JobRegister updateById(JobRegisterDTO jobRegisterDTO) {
        Optional<JobRegister> jobRegisterOptional = jobRegisterRepository.findById(jobRegisterDTO.getId());
        if (jobRegisterOptional.isPresent()){
            JobRegister jobRegister = jobRegisterMapper.toEntity(jobRegisterDTO);
            jobRegisterRepository.save(jobRegister);
            jobRegister.setId(jobRegisterDTO.getId());
            return jobRegisterOptional.get();
        }
        return null;
    }

//    @Override
//    public Job updateStatus(StatusJobDTO statusJobDTO) {
//        Job job = jobRepository.findById(statusJobDTO.getJobId()).get();
//        job.setStatusJob(statusJobRepository.findStatusById(statusJobDTO.getStatusId()));
//        return jobRepository.save(job);
//    }

    @Override
    public JobRegister updateStatusJobRegister(StatusJobRegisterDTO statusJobRegisterDTO) {
        JobRegister jobRegister = jobRegisterRepository.findById(statusJobRegisterDTO.getJobRegisterId()).get();
        jobRegister.setStatusJobRegister(statusJobRegisterRepository.findStatusById(statusJobRegisterDTO.getStatusJobRegisterId()));
        return jobRegisterRepository.save(jobRegister);
    }

    public Page<JobRegisterDTO> findAlD(Pageable pageable){
        return (Page<JobRegisterDTO>) jobRegisterRepository.findAll((Sort) pageable).stream().map(jobRegisterMapper::toDto);
    }

    @Override
    public int countAll() {
        return jobRegisterRepository.countAll();
    }

    @Override
    public int countJobRegByStatus(Long statusId, String smallDate, String bigDate) {
        return jobRegisterRepository.countJobRegisterByStatus(statusId, smallDate,  bigDate);
    }


}
