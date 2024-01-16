package com.github.kostia.ecohub.flows.department_disaster;

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
@Table("department_disaster")
public class DepartmentDisaster {

    @Id
    @Column("dep_dis_id")
    private Integer id;

    @Column("department_id")
    private Integer departmentId;

    @Column("disaster_id")
    private Integer disasterId;

    @Column("common_description")
    private String description;
}

