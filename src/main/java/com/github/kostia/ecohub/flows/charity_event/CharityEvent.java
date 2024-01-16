package com.github.kostia.ecohub.flows.charity_event;

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
@Table("charity_event")
public class CharityEvent {

    @Id
    @Column("event_id")
    private Integer id;

    @Column("head_leader_id")
    private Integer headLeaderId;

    @Column("department_id")
    private Integer departmentId;

    @Column("funds_raised")
    private Double fundsRaised;
}

