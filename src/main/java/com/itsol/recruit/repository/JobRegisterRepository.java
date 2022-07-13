package com.itsol.recruit.repository;

import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.repository.repoext.JobRegisterRepositoryExt;
import com.itsol.recruit.repository.repoext.JobRepositoryExt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRegisterRepository extends JpaRepository<JobRegister, Long>, JobRegisterRepositoryExt {

    @Query("SELECT  jobRegister FROM job_register jobRegister")
    Page<JobRegister> findAllOrderByDateAsc(Pageable pageable, Specification<JobRegister> where);
    Optional<JobRegister> findById(Long id);


}
