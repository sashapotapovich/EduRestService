package com.example.demo.service;

import com.example.demo.entity.Activity;
import java.util.List;

public interface ActivityService {
    void addMember(Activity activity);
    List<Activity>findAll();
}
