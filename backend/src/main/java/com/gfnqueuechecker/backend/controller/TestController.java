package com.gfnqueuechecker.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test2")
    public ResponseEntity<?> getLoggedUser(){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
