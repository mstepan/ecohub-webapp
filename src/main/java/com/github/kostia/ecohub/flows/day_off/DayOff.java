package com.github.kostia.ecohub.flows.day_off;

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
@Table("day_off")
public class DayOff {

    @Id
    @Column("day_off_id")
    private Integer id;

    @Column("employee_id")
    private Integer employeeId;

    @Transient
    Employee employee;

    @Column("number_of_days")
    private Integer numberOfDays;

    @Column("reason")
    private String reason;

    @Column("start_date")
    private Date startDate;

    @Column("end_date")
    private Date endDate;
}


