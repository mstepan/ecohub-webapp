package com.github.kostia.ecohub.flows.department;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Integer>{

    @Query("SELECT D.* FROM department D JOIN location L ON D.location_id = L.location_id")
    List<Department> findAllWithLocations();

}


