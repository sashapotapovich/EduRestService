package com.example.demo;

import com.example.demo.entity.Activity;
import com.example.demo.repository.ActivityRepository;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class DemoApplication {

    @Bean
    public CommandLineRunner commandLineRunner(ActivityRepository activityRepository) {
        return (args) -> {
            Activity activity = activityRepository.insert(new Activity("title", "summary", "description",
                                                                       LocalDateTime.now(), LocalDateTime.now(), "info"));
            log.error(activity.toString());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
