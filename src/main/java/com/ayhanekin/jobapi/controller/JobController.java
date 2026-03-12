package com.ayhanekin.jobapi.controller;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class JobController {

    private final JobService service;


    @GetMapping("jobs")
    public ResponseEntity<Slice<JobResponse>> getAllJobPosts(@PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.ok(service.getAllJobs(pageable));
    }

    @GetMapping("jobs/{id}")
    public JobResponse getJobPost(@PathVariable UUID id) {

        return service.getJobPost(id);
    }

    @PostMapping("jobs")
    public JobResponse createJob(@Valid @RequestBody CreateJobRequest jobRequest) {
        return service.save(jobRequest);
    }

    @PutMapping("jobs/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateJobRequest request) {

        JobResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("jobs/{id}")
    public void deleteJob(@PathVariable UUID id) {
        service.delete(id);
    }
}
