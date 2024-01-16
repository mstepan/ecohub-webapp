package com.github.kostia.ecohub.flows.manager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends CrudRepository<Manager, Integer>{
}


