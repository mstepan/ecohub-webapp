package com.github.kostia.ecohub.flows.disaster;

import com.github.kostia.ecohub.flows.location.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisasterRepo extends CrudRepository<Disaster, Integer>{
}


