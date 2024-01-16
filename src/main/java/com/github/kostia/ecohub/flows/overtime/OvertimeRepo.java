package com.github.kostia.ecohub.flows.overtime;

import com.github.kostia.ecohub.flows.department.Department;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeRepo extends CrudRepository<Overtime, Integer>{

    @Query("SELECT * FROM overtime WHERE job_history_id = :jobHistoryId")
    List<Overtime> findAllOvertimesForJobHistoryId(@Param("jobHistoryId") Integer jobHistoryId);

}


