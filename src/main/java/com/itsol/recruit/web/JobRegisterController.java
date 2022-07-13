package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobRegisterDTO;
import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.entity.JobRegister;
import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.service.JobRegisterService;
import com.itsol.recruit.service.impl.JobRegisterServiceImp;
import com.itsol.recruit.service.mapper.JobRegisterMapper;
import com.itsol.recruit.validation.EmailFormatValidation;
import com.itsol.recruit.web.vm.PageVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    private JobRegisterMapper jobRegisterMapper;

    @PostMapping("/job_register")
    public ResponseEntity<List<JobRegisterDTO>> getAllDESC(@RequestBody PageVM pageVM ,@RequestParam(value = "search", required = false ) String search, @RequestParam(value = "sortBy", required = false) String sortBy){

        Page<JobRegisterDTO> page = jobRegisterService.getAllJobRegister(pageVM, search, sortBy);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/job_register{id}")
    public ResponseEntity<Optional<JobRegister>> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(jobRegisterService.findById(id));
    }
    @PostMapping("/job_register/add")
    public ResponseEntity<?>add(@Valid @RequestBody JobRegisterDTO dto){
        jobRegisterService.addJobRegister(dto);
        return ResponseEntity.ok().body("OK");
    }
    @DeleteMapping("/job_register/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        jobRegisterService.deleteById(id);
        return ResponseEntity.ok().body("delete successfully");
    }
    @PutMapping("/job_register/update/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable Long id, @RequestBody JobRegisterDTO jobRegisterDTO){
//       jobRegisterMapper.toEntity(jobRegisterDTO);
        jobRegisterService.updateById(jobRegisterDTO);
       return ResponseEntity.ok().body((new ResponseObject(HttpStatus.OK, "update thành công", "")));
    }


}
