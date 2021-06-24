package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends CrudRepository<Config, Long> {

}