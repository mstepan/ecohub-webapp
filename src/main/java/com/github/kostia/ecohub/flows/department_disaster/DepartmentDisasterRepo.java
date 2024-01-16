package com.github.kostia.ecohub.flows.department_disaster;

import com.github.kostia.ecohub.flows.disaster.Disaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDisasterRepo extends CrudRepository<DepartmentDisaster, Integer>{
}


