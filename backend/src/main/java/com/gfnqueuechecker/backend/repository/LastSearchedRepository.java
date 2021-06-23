package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.LastSearched;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastSearchedRepository extends CrudRepository<LastSearched, Long> {

}
