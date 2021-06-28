package com.gfnqueuechecker.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {

        try {
            ProcessThreads.initialize();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(ProcessThreads::lastSearchedThread);
        thread2.start();

        Thread thread3 = new Thread(ProcessThreads::checkQueueThread);
        thread3.start();

        SpringApplication.run(BackendApplication.class, args);
    }



}
