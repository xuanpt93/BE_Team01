package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.JobDTO;
import com.itsol.recruit.entity.Job;
import com.itsol.recruit.service.JobService;
import com.itsol.recruit.service.impl.JobServiceImpl;
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
    public ResponseEntity<List<Job>> getAllDESC() {
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
    public ResponseEntity<?> delete(@PathVariable Long id){
        jobService.deleteById(id);
        return ResponseEntity.ok().body("successfull");
    }
}
