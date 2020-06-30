package com.example.demo.service.impl;

import com.example.demo.entity.Activity;
import com.example.demo.exception.ActivityManagementException;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.service.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService<Activity> {

    private final ActivityRepository activityRepository;

    @Autowired
    private ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity addMember(Activity activity) {
        if (activity.getId() != null) {
            activityRepository.findById(activity.getId()).orElseThrow(ActivityManagementException::new);
        }
        return activityRepository.insert(activity);
    }
    
    public List<Activity> findAll(){
        return activityRepository.findAll();
    }
}
