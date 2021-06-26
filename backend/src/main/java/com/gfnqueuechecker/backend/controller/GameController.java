package com.gfnqueuechecker.backend.controller;

import com.gfnqueuechecker.backend.ErrorMessage;
import com.gfnqueuechecker.backend.entity.Game;
import com.gfnqueuechecker.backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNew(@RequestBody Game game) {
        if(game.getAppId() == 0 || game.getGameName() == null || game.getGameName().trim().isEmpty()
                || game.getCoverImg() == null || game.getCoverImg().trim().isEmpty()){
            return ErrorMessage.send("Fill in all fields! Error!", HttpStatus.BAD_REQUEST);
        }

        if(this.gameService.getByAppIdOrGameName(game.getAppId(), game.getGameName()) != null){
            return ErrorMessage.send("This game already exists!", HttpStatus.BAD_REQUEST);
        }

        Game newGame = gameService.addNew(game);
        if (newGame == null) {
            return ErrorMessage.send("The game has not been added! Error!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(newGame, HttpStatus.OK);
    }

}
