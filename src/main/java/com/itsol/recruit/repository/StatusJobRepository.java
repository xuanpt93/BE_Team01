package com.itsol.recruit.repository;

import com.itsol.recruit.entity.StatusJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusJobRepository extends JpaRepository<StatusJob, Long> {
}
