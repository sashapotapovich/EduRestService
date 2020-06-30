package com.example.demo;

import com.example.demo.entity.Activity;
import com.example.demo.repository.ActivityRepository;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    @Bean
    public CommandLineRunner commandLineRunner(ActivityRepository activityRepository) {
        return (args) -> {
            Activity activity = activityRepository.insert(new Activity("title", "summary", "description",
                                                                       LocalDateTime.now(), LocalDateTime.now(), "info"));
            System.out.println(activity);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
