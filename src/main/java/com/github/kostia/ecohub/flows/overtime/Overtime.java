package com.github.kostia.ecohub.flows.overtime;

import com.github.kostia.ecohub.flows.job_history.JobHistory;
import com.github.kostia.ecohub.flows.location.Location;
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
@Table("overtime")
public class Overtime {

    @Id
    @Column("overtime_id")
    private Integer id;

    @Column("job_history_id")
    private Integer jobHistoryId;

    @Transient
    private JobHistory jobHistory;

    @Column("start_date")
    private Date startDate;

    @Column("end_date")
    private Date endDate;

}

