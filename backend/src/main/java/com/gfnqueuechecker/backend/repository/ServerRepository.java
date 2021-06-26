package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.Server;
import com.gfnqueuechecker.backend.entity.ServerGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends CrudRepository<Server, Long> {

    Server findByIdAndServerGroup(Long id, ServerGroup serverGroup);
}
