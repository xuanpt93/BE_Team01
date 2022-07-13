package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<Job> getAllJob();

    Optional<Job> findById(Long id);

    Job addJob(JobDTO jobDTO);

    void deleteById(Long id);

    Job updateById(JobDTO jobDTO, Long id);

    /*
    *chinhnd
     */

    int countJobPublished();

    int countAllJobDueSoon();

    int countViewjob();

    int countJobNeedManStepMonth(int month);
}
