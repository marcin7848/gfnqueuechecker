package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.CheckQueue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckQueueRepository extends CrudRepository<CheckQueue, Long> {

}