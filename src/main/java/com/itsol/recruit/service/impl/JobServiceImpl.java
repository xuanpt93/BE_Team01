package com.itsol.recruit.service.impl;
import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.repository.JobRepository;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.mapper.JobMapper;
import com.itsol.recruit.specification.JobSpecification;
import com.itsol.recruit.web.vm.PageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    public Job updateById(JobDTO jobDTO) {
        Optional<Job> jobOptional = jobRepository.findById(jobDTO.getId());
        if (jobOptional.isPresent()){
        Job job = jobMapper.toEntity(jobDTO);
        jobRepository.save(job);
        job.setId(jobDTO.getId());
        return jobOptional.get();
    }
        return null;
    }


    @Override
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    /*
    *Chinhnd
    * Them moi
     */

    @Override
    public int countJobPublished() {
        return jobRepository.countJobPublished();
    }

    @Override
    public int countAllJobDueSoon() {
        return jobRepository.countAllJobDueSoon();
    }

    @Override
    public int countViewjob() {
        return jobRepository.countViewJob();
    }

    @Override
    public int countJobNeedManStepMonth(int month) {
        try {
            return jobRepository.countJobNeedManStepMonth(month);
        } catch(Exception e){
            return 0;

        }
    }


    @Override
    public Job findJobByName(String jobName) {
        return jobRepository.findJobByName(jobName);
    }

    @Override
    public Page<JobDTO> getAllJobs(PageVM pageVM, String search, String sortBy) {
        Pageable firstPageWithTwoElements;
        if(sortBy == null){
            firstPageWithTwoElements = PageRequest.of(pageVM.getPageNumber(), pageVM.getPageSize());
        }else {
            firstPageWithTwoElements = PageRequest.of(pageVM.getPageNumber(), pageVM.getPageSize(), Sort.by(sortBy));
        }
        Specification<Job> where = JobSpecification.buildWhere(search);
        return  jobRepository.findAll(where, firstPageWithTwoElements).map(jobMapper::toDto);
    }

    @Override
    public void updateViewBy(Long id) {
        Job job = jobRepository.getById(id);
        job.setViews(job.getViews()+1);
        job.setId(id);
        jobRepository.save(job);
    }


}
