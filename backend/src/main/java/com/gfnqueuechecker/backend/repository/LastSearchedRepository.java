package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.LastSearched;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LastSearchedRepository extends CrudRepository<LastSearched, Long> {

    List<LastSearched> getAllByOrderBySearchTimeDesc(Pageable p);


}
