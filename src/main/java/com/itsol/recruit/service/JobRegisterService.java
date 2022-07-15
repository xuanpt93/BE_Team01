package com.itsol.recruit.service;

import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.web.vm.PageVM;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface JobRegisterService {
    Page<JobRegisterDTO> getAllJobRegister(PageVM pageVM, String search, String sortBy);
    Optional<JobRegister> findById(Long id);
    JobRegister addJobRegister(JobRegisterDTO dto);
    void deleteById(Long id);
    JobRegister updateById(JobRegisterDTO jobRegisterDTO);

    JobRegisterDTO save(JobRegisterDTO jobRegisterDTO);

    /*
     * trungnd
     */
    int countAll();

    int countJobRegByStatus(Long statusId,String smallDate, String bigDate);

    int countSuccessfullJobReg(int param);

}
