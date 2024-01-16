package com.github.kostia.ecohub.flows.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("job")
public class Job {

    @Id
    @Column("job_id")
    private Integer id;

    @Column("job_type")
    private String type;

    @Column("min_salary")
    private Double minSalary;

    @Column("max_salary")
    private Double maxSalary;

}

