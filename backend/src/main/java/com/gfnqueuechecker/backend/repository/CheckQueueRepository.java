package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.CheckQueue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckQueueRepository extends CrudRepository<CheckQueue, Long> {

    List<CheckQueue> findBySearchKey(String searchKey);

}