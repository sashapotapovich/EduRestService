package com.example.demo.service.impl;

import com.example.demo.entity.Activity;
import com.example.demo.exception.ActivityManagementException;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.service.ActivityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    private ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public void addMember(Activity justiceLeagueMember) {
        activityRepository.findById(justiceLeagueMember.getId()).orElseThrow(ActivityManagementException::new);
        
        activityRepository.insert(justiceLeagueMember);
    }
    
    public List<Activity> findAll(){
        return activityRepository.findAll();
    }
}
