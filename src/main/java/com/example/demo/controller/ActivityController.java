package com.example.demo.controller;

import com.example.demo.entity.Activity;
import com.example.demo.service.ActivityService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService<Activity> activityService;

    public ActivityController(ActivityService<Activity> activityService) {
        this.activityService = activityService;
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE
    }, consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public Activity addActivity(@Valid @RequestBody Activity activity) {
        try {
            activityService.addMember(activity);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return activity;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Activity> getAllUsers() {
        System.out.println("Getting Activities");
        return activityService.findAll();
    }
}