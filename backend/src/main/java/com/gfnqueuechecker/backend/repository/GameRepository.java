package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

}
