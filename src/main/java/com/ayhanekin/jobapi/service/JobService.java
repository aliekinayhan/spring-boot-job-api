package com.ayhanekin.jobapi.service;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.mapper.JobMapper;
import com.ayhanekin.jobapi.model.JobPost;
import com.ayhanekin.jobapi.repo.JobRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JobService {

    private final JobRepo repo;
    private final JobMapper mapper;


    public List<JobResponse> getAllJobs() {
        return mapper.toResponseList(repo.findAll());
    }

    public JobResponse getJobPost(UUID id) {
        return mapper.toResponse(repo.findById(id).orElse(new JobPost()));
    }

    public JobResponse save(CreateJobRequest request) {
        return mapper.toResponse(repo.save(mapper.createJobPost(request)));
    }

    public void update(UUID id, UpdateJobRequest jobRequest) {
        JobPost jobPost = repo.findById(id).orElseThrow(() ->
                (new RuntimeException("Job not found")));

        mapper.updateJobPost(jobRequest,jobPost);
        repo.save(jobPost);


    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
