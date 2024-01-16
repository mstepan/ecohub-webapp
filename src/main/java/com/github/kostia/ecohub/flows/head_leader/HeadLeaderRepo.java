package com.github.kostia.ecohub.flows.head_leader;

import com.github.kostia.ecohub.flows.location.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadLeaderRepo extends CrudRepository<HeadLeader, Integer>{
}


