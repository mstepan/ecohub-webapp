package com.github.kostia.ecohub.flows.job_history;

import com.github.kostia.ecohub.flows.employee.Employee;
import com.github.kostia.ecohub.flows.job.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("employee_job_history")
public class JobHistory {

    @Id
    @Column("job_history_id")
    private Integer id;

    @Column("job_id")
    private Integer jobId;

    @Transient
    Job job;

    @Column("employee_id")
    private Integer employeeId;

    @Transient
    Employee employee;

    @Column("salary")
    private Double salary;

    @Column("bonus")
    private Double bonus;

    @Column("start_date")
    private Date startDate;

    @Column("end_date")
    private Date endDate;

}

