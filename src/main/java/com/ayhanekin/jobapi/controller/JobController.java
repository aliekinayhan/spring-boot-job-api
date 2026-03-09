package com.ayhanekin.jobapi.controller;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.model.JobPost;
import com.ayhanekin.jobapi.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class JobController {

    private final JobService service;


    @GetMapping("/jobs")
    public List<JobResponse> getAllJobPosts() {

        return service.getAllJobs();
    }

    @GetMapping("/jobs/{id}")
    public JobResponse getJobPost(@PathVariable UUID id) {

        return service.getJobPost(id);
    }

    @PostMapping("/jobs")
    public JobResponse createJob(@Valid @RequestBody CreateJobRequest jobRequest) {
        return service.save(jobRequest);
    }

    @PutMapping("/jobs/{id}")
    public void updateJob(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateJobRequest request) {

        service.update(id, request);
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable UUID id) {
        service.delete(id);
    }
}
