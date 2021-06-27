package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.entity.Game;
import com.gfnqueuechecker.backend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game addNew(Game game){
        return this.gameRepository.save(game);
    }

    public Game getByAppIdOrGameName(Long appId, String gameName){
        return this.gameRepository.findByAppIdOrGameName(appId, gameName);
    }

    public Iterable<Game> getAll(){
        return this.gameRepository.findAll();
    }

}
