package com.itsol.recruit.repository;

import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.repository.repoext.JobRepositoryExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryExt {

    @Query("SELECT jobs FROM job jobs  ORDER BY jobs.dueDate ASC")
    List<Job> findAllOrderByDateAsc();

    Optional<Job> findById(Long id);

//    void deleteById(Long id);

}
