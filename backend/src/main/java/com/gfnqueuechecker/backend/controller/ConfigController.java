package com.gfnqueuechecker.backend.controller;

import com.gfnqueuechecker.backend.ErrorMessage;
import com.gfnqueuechecker.backend.entity.Config;
import com.gfnqueuechecker.backend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/getForSettings")
    public ResponseEntity<?> getLastSearched(){
        return new ResponseEntity<>(this.configService.getConfigsForSettings(), HttpStatus.OK);
    }

    @PostMapping("/editSettings")
    public ResponseEntity<?> editSettings(@RequestBody List<Config> configs) {
        Iterable<Config> updatedSettings = configService.updateConfigsSettings(configs);
        if (updatedSettings == null) {
            return ErrorMessage.send("The setting configs have not been edited! Error!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedSettings, HttpStatus.OK);
    }
}
