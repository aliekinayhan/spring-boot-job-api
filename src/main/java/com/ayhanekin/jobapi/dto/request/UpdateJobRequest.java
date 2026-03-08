package com.ayhanekin.jobapi.dto.request;

import com.ayhanekin.jobapi.enums.JobType;
import com.ayhanekin.jobapi.enums.WorkType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 256, message = "Title must be less than 256 characters")
    private String title;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    private BigDecimal salary;

    @NotNull(message = "Experience cannot be null")
    @Min(value = 0, message = "Experience cannot be negative")
    private Integer experience;

    @Size(max = 5000, message = "Description too long")
    private String description;

    @NotEmpty(message = "Tech stack cannot be empty")
    private List<String> techStack;

    @NotNull(message = "Work type is required")
    private WorkType workType;

    @NotNull(message = "Job type is required")
    private JobType jobType;

}
