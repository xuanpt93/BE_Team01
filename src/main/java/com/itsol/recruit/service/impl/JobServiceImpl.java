package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    /*public final JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }*/

    @Autowired
    public  JobRepository jobRepository;

    @Autowired
    JobMapper jobMapper;

    @Override
    public List<Job> getAllJob() {

        return jobRepository.findAllOrderByDateAsc();
    }

    @Override
    public Optional<Job> findById(Long id) {

        return jobRepository.findById(id);
    }

    @Override
    public Job addJob(JobDTO jobDTO) {
        Job job = jobMapper.toEntity(jobDTO);
        return jobRepository.save(job);
    }

    @Override
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    /*@Override
    public Optional<Job> updateById(Long id) {
        Optional<Job> jobOptional = findById(id);
        return Optional.empty();
    }*/
}
