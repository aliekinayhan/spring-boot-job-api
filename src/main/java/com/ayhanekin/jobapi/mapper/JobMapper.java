package com.ayhanekin.jobapi.mapper;

import com.ayhanekin.jobapi.dto.request.CreateJobRequest;
import com.ayhanekin.jobapi.dto.request.UpdateJobRequest;
import com.ayhanekin.jobapi.dto.response.JobResponse;
import com.ayhanekin.jobapi.model.JobPost;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

//CreateJobRequest → JobPost

@Mapper(componentModel = "spring")
public interface JobMapper {

    // at the compile time it will check the return type and parameters and it will
    // produce correct mapping codes in java

    JobPost createJobPost(CreateJobRequest jobRequest);

    void updateJobPost(UpdateJobRequest jobRequest, @MappingTarget JobPost jobpost);

    JobResponse toResponse(JobPost jobPost);


    List<JobResponse> toResponseList(List<JobPost> jobs);

}
