package com.github.kostia.ecohub.flows.job;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends CrudRepository<Job, Integer>{
}


