package com.ayhanekin.jobapi.repo;

import com.ayhanekin.jobapi.model.JobPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepo extends JpaRepository<JobPost, UUID> {

    Slice<JobPost> findAllBy(Pageable pageable);
}
