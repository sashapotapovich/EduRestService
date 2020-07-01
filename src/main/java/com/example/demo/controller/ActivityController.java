package com.example.demo.controller;

import com.example.demo.entity.Activity;
import com.example.demo.service.ActivityService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService<Activity> activityService;

    public ActivityController(ActivityService<Activity> activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/{id}")
    public Activity getActivity(@PathVariable String id) {
        log.debug("Trying to get Activity for the id - {}", id);
        return activityService.findById(id);
    }

    @GetMapping("/list")
    public List<Activity> getAllActivities() {
        log.debug("Trying to get all Activities");
        return activityService.findAll();
    }

    @PostMapping
    public Activity addActivity(@Valid @RequestBody Activity activity) {
        log.debug("Adding new Activity");
        activityService.addActivity(activity);
        return activity;
    }


    @PostMapping("/{id}")
    public Activity updateActivity(@PathVariable String id,
                                   @Valid @RequestBody Activity activity) {
        log.debug("Trying to update Activity for the id - {}", id);
        return activityService.update(id, activity);
    }

    @DeleteMapping("/{id}")
    public Activity deleteActivity(@PathVariable String id) {
        log.debug("Trying to delete Activity for the id - {}", id);
        return activityService.deleteActivity(id);
    }
}