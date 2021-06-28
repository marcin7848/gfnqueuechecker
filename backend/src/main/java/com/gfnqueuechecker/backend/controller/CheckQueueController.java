package com.gfnqueuechecker.backend.controller;

import com.gfnqueuechecker.backend.ErrorMessage;
import com.gfnqueuechecker.backend.entity.Game;
import com.gfnqueuechecker.backend.service.CheckQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkQueue")
public class CheckQueueController {

    private final CheckQueueService checkQueueService;

    @Autowired
    public CheckQueueController(CheckQueueService checkQueueService) {
        this.checkQueueService = checkQueueService;
    }


}
