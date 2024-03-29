package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.ServerGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerGroupRepository extends CrudRepository<ServerGroup, Long> {

    @Override
    List<ServerGroup> findAll();
}