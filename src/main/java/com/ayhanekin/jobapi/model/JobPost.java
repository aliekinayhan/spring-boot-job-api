package com.ayhanekin.jobapi.model;

import com.ayhanekin.jobapi.enums.JobType;
import com.ayhanekin.jobapi.enums.WorkType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobPost {

    @Id
    @Column(name = "job_id")
    private UUID id;

    @Column(nullable = false, length = 256)
    private String title;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(nullable = false)
    private Integer experience;

    @Column(length = 5000)
    private String description;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "job_tech_stack", joinColumns = @JoinColumn(name = "job_id"))
    private List<String> techStack;

    @Enumerated(EnumType.STRING)
    private WorkType workType;

    @Enumerated(EnumType.STRING)
    private JobType jobType;
}
