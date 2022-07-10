package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.impl.JobRegisterServiceImp;
import com.itsol.recruit.service.mapper.JobRegisterMapper;
import com.itsol.recruit.validation.EmailFormatValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class JobRegisterController  {

    @Autowired
    private JobRegisterServiceImp jobRegisterService;
    private JobRegisterMapper jobRegisterMapper;

    @GetMapping("/job_register")
    public ResponseEntity<List<JobRegister>> getAllDESC(){
        return ResponseEntity.ok().body(jobRegisterService.getAllJobRegister());
    }

    @GetMapping("/job_register{id}")
    public ResponseEntity<Optional<JobRegister>> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(jobRegisterService.findById(id));
    }
    @PostMapping("/job_register/add")
    public ResponseEntity<?>add(@Valid @RequestBody JobRegisterDTO dto){
        jobRegisterService.addJobRegister(dto);
        return ResponseEntity.ok().body("successfully");

    }
    @DeleteMapping("/job_register/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        jobRegisterService.deleteById(id);
        return ResponseEntity.ok().body("delete successfully");
    }
    @PutMapping("/job_register/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody JobRegisterDTO jobRegisterDTO){
//       jobRegisterMapper.toEntity(jobRegisterDTO);
        jobRegisterService.updateById(jobRegisterDTO, id);
       return ResponseEntity.ok().body("update successfully");
    }


    @GetMapping("/page")
    public ResponseEntity<List<JobRegisterDTO>> getall(Pageable pageable){
        Page<JobRegisterDTO>  jobRegisterDTOPage = jobRegisterService.findAlD(pageable);
        return ResponseEntity.ok().body(jobRegisterDTOPage.getContent());
    }

    @GetMapping("/page1")
    public List<JobRegisterDTO> getAllItemCategoryByPage(@RequestParam("page") int pageIndex,
                                                       @RequestParam("size") int pageSize){

        return jobRegisterService
                .findAlD((Pageable) PageRequest.of(pageIndex, pageSize)).getContent();
    }
}
