package com.ayhanekin.jobapi.repo;

import com.ayhanekin.jobapi.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer> {
}
