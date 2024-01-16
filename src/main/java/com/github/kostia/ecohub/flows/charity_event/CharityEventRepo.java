package com.github.kostia.ecohub.flows.charity_event;

import com.github.kostia.ecohub.flows.department_disaster.DepartmentDisaster;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharityEventRepo extends CrudRepository<CharityEvent, Integer>{

    @Query("SELECT * FROM charity_event WHERE head_leader_id = :headLeaderId")
    List<CharityEvent> findAllCharityEventsForLeaderId(@Param("headLeaderId") Integer headLeaderId);

}


