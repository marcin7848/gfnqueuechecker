package com.gfnqueuechecker.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.gfnqueuechecker.backend.repository"})
@PropertySource(value = {"classpath:application.properties"})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.gfnqueuechecker.backend.service", "com.gfnqueuechecker.backend.repository",
        "com.gfnqueuechecker.backend.controller", "com.gfnqueuechecker.backend.entity", "com.gfnqueuechecker.backend"})
public class RootConfig {

}
