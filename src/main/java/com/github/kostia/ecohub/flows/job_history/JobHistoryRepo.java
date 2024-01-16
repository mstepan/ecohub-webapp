package com.github.kostia.ecohub.flows.job_history;

import com.github.kostia.ecohub.flows.charity_event.CharityEvent;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepo extends CrudRepository<JobHistory, Integer>{

    @Query("SELECT * FROM employee_job_history WHERE employee_id = :employeeId")
    List<JobHistory> findJobHistoryForEmployeeId(@Param("employeeId") Integer employeeId);

}


