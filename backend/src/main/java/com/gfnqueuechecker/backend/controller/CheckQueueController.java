package com.gfnqueuechecker.backend.controller;

import com.gfnqueuechecker.backend.ErrorMessage;
import com.gfnqueuechecker.backend.entity.Game;
import com.gfnqueuechecker.backend.service.CheckQueueService;
import com.gfnqueuechecker.backend.service.GameService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkQueue")
public class CheckQueueController {

    private final CheckQueueService checkQueueService;
    private final GameService gameService;

    @Autowired
    public CheckQueueController(CheckQueueService checkQueueService, GameService gameService) {
        this.checkQueueService = checkQueueService;
        this.gameService = gameService;
    }


    @PostMapping("/generate/game/{gameId}")
    public ResponseEntity<?> generateCheckQueue(@PathVariable("gameId") Long gameId) {
        if(gameId == 0){
            return ErrorMessage.send("Wrong gameId! Error!", HttpStatus.BAD_REQUEST);
        }

        Game game = this.gameService.getById(gameId);
        if(game == null){
            return ErrorMessage.send("Wrong gameId! Error!", HttpStatus.BAD_REQUEST);
        }

        String searchKey = this.checkQueueService.generateCheckQueue(game);

        if(searchKey.equals("")){
            return ErrorMessage.send("Generating checkQueue went wrong!", HttpStatus.BAD_REQUEST);
        }

        JSONObject resp = new JSONObject();
        resp.put("searchKey", searchKey);

        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }

    @GetMapping("/get/searchKey/{searchKey}")
    public ResponseEntity<?> getBySearchKey(@PathVariable("searchKey") String searchKey){
        return new ResponseEntity<>(this.checkQueueService.getBySearchKey(searchKey), HttpStatus.OK);
    }
}
