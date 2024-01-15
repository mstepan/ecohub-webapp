package com.github.kostia.ecohub.flows.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LocationRepo extends CrudRepository<Location, Integer>{
}


