package com.github.kostia.ecohub.flows.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
//@Accessors(fluent = false, chain = false)
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

