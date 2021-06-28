package com.gfnqueuechecker.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {

        Thread thread = new Thread(ProcessThreads::lastSearchedThread);
        thread.start();

        Thread thread2 = new Thread(ProcessThreads::checkQueueThread);
        thread2.start();

        SpringApplication.run(BackendApplication.class, args);
    }



}
