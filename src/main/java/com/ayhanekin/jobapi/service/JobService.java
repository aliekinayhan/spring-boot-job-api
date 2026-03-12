package com.ayhanekin.jobapi.service;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.dto.response.SliceResponse;
import com.ayhanekin.jobapi.exception.ResourceNotFoundException;
import com.ayhanekin.jobapi.mapper.JobMapper;
import com.ayhanekin.jobapi.model.JobPost;
import com.ayhanekin.jobapi.repo.JobRepo;
import com.github.f4b6a3.uuid.UuidCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JobService {

    private final JobRepo repo;
    private final JobMapper mapper;


    @Transactional(readOnly = true)
    public SliceResponse<JobResponse> getAllJobs(Pageable pageable) {
        Slice<JobPost> jobPosts = repo.findAllBy(pageable);
        List<JobResponse> responses = jobPosts
                .getContent()
                .stream().
                map(mapper::toResponse)
                .toList();

        return SliceResponse.
                <JobResponse>builder()
                .content(responses)
                .hasNext(jobPosts.hasNext())
                .build();
    }

    @Transactional(readOnly = true)
    public JobResponse getJobPost(UUID id) {
        return mapper.toResponse(repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id)));
    }

    @Transactional
    public JobResponse save(CreateJobRequest request) {
        JobPost job = mapper.createJobPost(request);
        job.setId(UuidCreator.getTimeOrderedEpoch());

        return mapper.toResponse(repo.save(job));
    }

    @Transactional
    public JobResponse update(UUID id, UpdateJobRequest jobRequest) {
        JobPost jobPost = repo.findById(id).orElseThrow(() ->
                (new ResourceNotFoundException("Job not found with id: " + id)));
        mapper.updateJobPost(jobRequest, jobPost);
        repo.save(jobPost);


        return mapper.toResponse(jobPost);

    }

    @Transactional
    public void delete(UUID id) {

        JobPost job = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException ("Job is not there already..."));

        repo.delete(job);

    }
}
