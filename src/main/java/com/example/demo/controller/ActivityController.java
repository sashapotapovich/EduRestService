package com.example.demo.controller;

import com.example.demo.entity.Activity;
import com.example.demo.exception.ActivityManagementException;
import com.example.demo.service.ActivityService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping
    public Activity addActivity(@Valid @RequestBody Activity activity) {
        try {
            activityService.addMember(activity);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return activity;
    }

    @GetMapping("/list")
    public List<Activity> getAllActivities() {
        log.info("Getting all Activities");
        return activityService.findAll();
    }

    @GetMapping("/{id}")
    public Activity getActivity(@PathVariable String id) {
        log.info("Getting Activity for id - {}", id);
        Optional<Activity> byId = activityService.findById(id);
        return byId.orElseThrow(() -> new ActivityManagementException("Activity not found, id - " + id));
    }
}