package com.article.multithreadbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling
//@SpringBootApplication

@EnableScheduling
@EnableAsync
@SpringBootApplication
@EnableJpaAuditing
public class MultithreadBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultithreadBatchApplication.class, args);
    }

}
