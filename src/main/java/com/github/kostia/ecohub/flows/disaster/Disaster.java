package com.github.kostia.ecohub.flows.disaster;

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
@Table("disaster")
public class Disaster {

    @Id
    @Column("disaster_id")
    private Integer id;

    @Column("disaster_type")
    private String type;

    @Column("casualties")
    private Integer casualties;

    @Column("total_cost")
    private Double totalCost;
}

