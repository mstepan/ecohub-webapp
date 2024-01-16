package com.github.kostia.ecohub.flows.employee;

import com.github.kostia.ecohub.flows.department.Department;
import com.github.kostia.ecohub.flows.manager.Manager;
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
@Table("employee")
public class Employee {

    @Id
    @Column("employee_id")
    private Integer id;

    @Column("department_id")
    private Integer departmentId;

    @Transient
    private Department department;

    @Column("manager_id")
    private Integer managerId;

    @Transient
    private Manager manager;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("gender")
    private String gender;

    @Column("age")
    private Integer age;

    @Column("email")
    private String email;

    @Column("hire_date")
    private Date hireDate;

    @Column("phone_number")
    private String phoneNumber;

}


