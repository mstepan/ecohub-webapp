package com.github.kostia.ecohub.flows.head_leader;

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
@Table("head_leader")
public class HeadLeader {

    @Id
    @Column("leader_id")
    private Integer id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

}

