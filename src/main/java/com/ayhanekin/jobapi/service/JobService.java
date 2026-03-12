package com.ayhanekin.jobapi.service;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.exception.ResourceNotFoundException;
import com.ayhanekin.jobapi.mapper.JobMapper;
import com.ayhanekin.jobapi.model.JobPost;
import com.ayhanekin.jobapi.repo.JobRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JobService {

    private final JobRepo repo;
    private final JobMapper mapper;


    public Slice<JobResponse> getAllJobs(Pageable pageable) {
        Slice<JobPost> jobs = repo.findAllBy(pageable);
        return jobs.map(mapper::toResponse);
    }

    public JobResponse getJobPost(UUID id) {
        return mapper.toResponse(repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id)));
    }

    public JobResponse save(CreateJobRequest request) {
        return mapper.toResponse(repo.save(mapper.createJobPost(request)));
    }

    public JobResponse update(UUID id, UpdateJobRequest jobRequest) {
        JobPost jobPost = repo.findById(id).orElseThrow(() ->
                (new ResourceNotFoundException("Job not found with id: " + id)));
        mapper.updateJobPost(jobRequest, jobPost);
        repo.save(jobPost);


        return mapper.toResponse(jobPost);

    }

    public void delete(UUID id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("This job already does not exist: " + id);
        } else {
            repo.deleteById(id);
        }
    }
}
