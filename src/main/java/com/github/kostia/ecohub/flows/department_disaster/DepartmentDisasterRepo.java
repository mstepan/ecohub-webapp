package com.github.kostia.ecohub.flows.department_disaster;

import com.github.kostia.ecohub.flows.department.Department;
import com.github.kostia.ecohub.flows.disaster.Disaster;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDisasterRepo extends CrudRepository<DepartmentDisaster, Integer>{

    @Query("SELECT DD.* FROM department_disaster DD JOIN disaster D ON D.disaster_id = DD.disaster_id WHERE DD.department_id = :departmentId")
    List<DepartmentDisaster> findAllDisastersForDepartmentId(@Param("departmentId") Integer departmentId);

}


