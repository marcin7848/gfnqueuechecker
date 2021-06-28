package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    Game findByAppIdOrGameName(Long appId, String gameName);
    List<Game> findGamesByGameNameContainingIgnoreCase(String gameName);

}
