package com.ayhanekin.jobapi.service;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.mapper.JobMapper;
import com.ayhanekin.jobapi.model.JobPost;
import com.ayhanekin.jobapi.repo.JobRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepo repo;

    @Autowired
    JobMapper mapper;

    public List<JobResponse> getAllJobs() {
        return mapper.toResponseList(repo.findAll());
    }

    public JobResponse getJobPost(int id) {

        return mapper.toResponse(repo.findById(id).orElse(new JobPost()));

    }

    public void save(CreateJobRequest requestJob) {
        repo.save(mapper.createJobPost(requestJob));
    }

    public void update(int id, UpdateJobRequest jobRequest) {
        JobPost jobPost = repo.findById(id).orElseThrow(() ->
                (new RuntimeException("Job not found")));

        mapper.updateJobPost(jobRequest,jobPost);
        repo.save(jobPost);


    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
