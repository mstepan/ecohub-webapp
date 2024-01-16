package com.github.kostia.ecohub.flows.day_off;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayOffRepo extends CrudRepository<DayOff, Integer>{

    @Query("SELECT * FROM day_off WHERE employee_id = :employeeId")
    List<DayOff> findDayOffsByEmployeeId(@Param("employeeId") Integer employeeId);

}


