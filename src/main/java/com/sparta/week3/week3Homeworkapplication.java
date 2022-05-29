package com.sparta.week3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class week3Homeworkapplication {

    public static void main(String[] args) {
        SpringApplication.run(week3Homeworkapplication.class, args);
    }

}



