package com.ayhanekin.jobapi.controller;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.dto.response.SliceResponse;
import com.ayhanekin.jobapi.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService service;


    @GetMapping()
    public ResponseEntity<SliceResponse<JobResponse>> getAllJobPosts(
            @PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.ok(service.getAllJobs(pageable));
    }

    @GetMapping("/{id}")
    public JobResponse getJobPost(@PathVariable UUID id) {
        return service.getJobPost(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public JobResponse createJob(@Valid @RequestBody CreateJobRequest jobRequest) {
        return service.save(jobRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateJobRequest request) {
        JobResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable UUID id) {
        service.delete(id);
    }
}
