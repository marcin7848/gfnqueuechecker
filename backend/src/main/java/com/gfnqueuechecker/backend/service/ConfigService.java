package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.entity.Config;
import com.gfnqueuechecker.backend.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {

    private final ConfigRepository configRepository;

    @Autowired
    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public List<Config> getAll(){
        return configRepository.findAll();
    }
}
