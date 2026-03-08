package com.ayhanekin.jobapi.controller;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.model.JobPost;
import com.ayhanekin.jobapi.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    JobService service;

    @GetMapping("/jobs")
    public List<JobResponse> getAllJobPosts() {

        return service.getAllJobs();
    }

    @GetMapping("/jobs/{jobId}")
    public JobResponse getJobPost(@PathVariable int jobId) {

        return service.getJobPost(jobId);
    }

    @PostMapping("/jobs")
    public void createJob(@Valid @RequestBody CreateJobRequest jobRequest) {
        service.save(jobRequest);
    }

    @PutMapping("/jobs/{id}")
    public void updateJob(
            @PathVariable int id,
            @Valid @RequestBody UpdateJobRequest request) {

        service.update(id, request);
    }

    @DeleteMapping("/jobs")
    public void deleteJob(@PathVariable int id) {
        service.delete(id);
    }
}
