package com.gfnqueuechecker.backend.service;
import com.gfnqueuechecker.backend.entity.Game;
import com.gfnqueuechecker.backend.repository.CheckQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckQueueService {

    private final CheckQueueRepository checkQueueRepository;
    private final GameService gameService;

    @Autowired
    public CheckQueueService(CheckQueueRepository checkQueueRepository, GameService gameService) {
        this.checkQueueRepository = checkQueueRepository;
        this.gameService = gameService;
    }

    public List<Game> getGamesByGameNameContaining(String gameName){
        return this.gameService.getByGameNameContaining(gameName);
    }

}
