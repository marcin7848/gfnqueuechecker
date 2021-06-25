package com.gfnqueuechecker.backend.service;

import com.gfnqueuechecker.backend.entity.Config;
import com.gfnqueuechecker.backend.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ConfigService {

    private final ConfigRepository configRepository;
    private final List<String> settingConfigs = List.of("Authorization", "X-Device-Id");

    @Autowired
    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public List<Config> getConfigsForSettings(){
        List<Config> configs = new ArrayList<>();
        settingConfigs.forEach(c -> {
            configs.add(configRepository.getConfigByConfigName(c));
        });
        configs.removeIf(Objects::isNull);
        return configs;
    }

    public Iterable<Config> updateConfigsSettings(List<Config> configs){
        List<Config> newConfigs = new ArrayList<>();

        settingConfigs.forEach(sc -> {
            Config authConfig = configRepository.getConfigByConfigName(sc);
            if(authConfig == null){
                newConfigs.add(new Config(0L, sc,
                        configs.stream().filter(c -> c.getConfigName().equals(sc)).findFirst()
                                .orElse(new Config(0L, "", "")).getConfigValue()
                ));
            }else{
                authConfig.setConfigValue(configs.stream().filter(c -> c.getConfigName().equals(sc))
                        .findFirst()
                        .orElse(new Config(0L, "", authConfig.getConfigValue())).getConfigValue());
                newConfigs.add(authConfig);
            }
        });

        return configRepository.saveAll(newConfigs);
    }
}
