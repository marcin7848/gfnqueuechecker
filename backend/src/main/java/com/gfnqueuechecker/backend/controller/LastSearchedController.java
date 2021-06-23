package com.gfnqueuechecker.backend.controller;

import com.gfnqueuechecker.backend.service.LastSearchedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lastSearched")
public class LastSearchedController {

    private final LastSearchedService lastSearchedService;

    @Autowired
    public LastSearchedController(LastSearchedService lastSearchedService) {
        this.lastSearchedService = lastSearchedService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getLastSearched(){
        return new ResponseEntity<>(this.lastSearchedService.getLastSearched(), HttpStatus.OK);
    }
}
