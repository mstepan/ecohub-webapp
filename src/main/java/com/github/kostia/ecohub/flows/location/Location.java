package com.github.kostia.ecohub.flows.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = false, chain = false)
public class Location {

    private String address;

    private String postalCode;

    private String city;

    private String country;

}
