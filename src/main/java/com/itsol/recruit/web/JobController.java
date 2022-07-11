package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.ResponseObject;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.impl.JobServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class JobController {
    public final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/job")
    public ResponseEntity<List<Job>> getAllASC() {
        return ResponseEntity.ok().body(jobService.getAllJob());
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Optional<Job>> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(jobService.findById(id));
    }

    @PostMapping("/job/add")
    /*public ResponseEntity<Job> add(@Valid  @RequestBody JobDTO dto){
        return ResponseEntity.ok().body(jobService.addJob(dto));
    }*/
    public ResponseEntity<?> add(@Valid @RequestBody JobDTO dto) {
        jobService.addJob(dto);
        return ResponseEntity.ok().body("successfull");
    }

    @DeleteMapping("/job/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        jobService.deleteById(id);
        return ResponseEntity.ok().body("successfull");
    }

    @PutMapping("/job/update")
    public ResponseEntity<ResponseObject> updateCategory(@RequestBody JobDTO jobDTO) {
        Job job = jobService.updateById(jobDTO);
        if(job == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(HttpStatus.BAD_REQUEST, "Job không tồn tại", ""));
        }
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, "Cập nhật Job thành công", jobService.updateById(jobDTO)));
    }
}

