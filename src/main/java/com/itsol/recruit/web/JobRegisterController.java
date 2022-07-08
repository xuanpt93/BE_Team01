package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.validation.EmailFormatValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)

public class JobRegisterController  {

    @Autowired
    private JobRegisterService jobRegisterService;

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
    @PutMapping("/job_register/delete/{id}")
    public ResponseEntity<?> update(@PathVariable Long id){
        jobRegisterService.updateById(id);
        return ResponseEntity.ok().body("update successfully");
    }



}
