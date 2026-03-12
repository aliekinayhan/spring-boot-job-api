package com.ayhanekin.jobapi.dto.response;

import com.ayhanekin.jobapi.enums.JobType;
import com.ayhanekin.jobapi.enums.WorkType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    private UUID id;
    private String title;
    private BigDecimal salary;
    private Integer experience;
    private String description;
    private List<String> techStack;
    private WorkType workType;
    private JobType jobType;

}
