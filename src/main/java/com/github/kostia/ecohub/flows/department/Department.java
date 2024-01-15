package com.github.kostia.ecohub.flows.department;

import com.github.kostia.ecohub.flows.location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table("department")
public class Department {

    @Id
    @Column("department_id")
    private Integer id;

    @Column("department_name")
    private String name;

    @Column("phone_number")
    private String phoneNumber;

    @Column("location_id")
    private Integer locationId;

//    @Transient
//    private Location location;

}

