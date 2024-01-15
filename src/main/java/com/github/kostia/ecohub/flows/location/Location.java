package com.github.kostia.ecohub.flows.location;

import com.github.kostia.ecohub.flows.department.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("location")
public class Location {

    @Id
    @Column("location_id")
    private Integer id;

    @Column("address")
    private String address;

    @Column("postal_code")
    private String postalCode;

    @Column("city")
    private String city;

    @Column("country")
    private String country;
}

