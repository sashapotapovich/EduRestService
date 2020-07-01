package com.example.demo;

import com.example.demo.entity.Activity;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.service.ActivityService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    
    @Autowired
    private ActivityRepository activityRepository; 
    @Autowired
    private ActivityService<Activity> activityService;
    
    
    @Test
    void addActivityTest(){
        activityService.addActivity(new Activity("title",
                                                 "summary",
                                                 "description",
                                                 LocalDateTime.now(),
                                                 LocalDateTime.now(),
                                                 "info"));
        activityService.addActivity(new Activity("title2",
                                                 "summary3",
                                                 "description4",
                                                 LocalDateTime.now(),
                                                 LocalDateTime.now(),
                                                 "info5"));
        int size = activityService.findAll().size();
        Assertions.assertEquals(2, size);
    }

    @AfterEach
    void clearDatabase(){
        activityRepository.deleteAll();
    }
}
