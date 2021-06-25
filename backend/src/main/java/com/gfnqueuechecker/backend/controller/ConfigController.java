package com.gfnqueuechecker.backend.controller;

import com.gfnqueuechecker.backend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getLastSearched(){
        return new ResponseEntity<>(this.configService.getAll(), HttpStatus.OK);
    }
}
