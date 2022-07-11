package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.web.vm.PageVM;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface JobRegisterService {
    Page<JobRegisterDTO> getAllJobRegister(PageVM pageVM);
    Optional<JobRegister> findById(Long id);
    JobRegister addJobRegister(JobRegisterDTO dto);
    void deleteById(Long id);
    JobRegister updateById(JobRegisterDTO jobRegisterDTO);

    JobRegisterDTO save(JobRegisterDTO jobRegisterDTO);
}
