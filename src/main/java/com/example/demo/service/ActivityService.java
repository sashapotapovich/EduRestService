package com.example.demo.service;

import com.example.demo.entity.Activity;
import java.util.List;
import java.util.Optional;

public interface ActivityService<T extends Activity> { //TODO: DO we need wildcard here
    T addMember(T activity);
    List<T>findAll();
    T findById(String id);
    T update(String id, T activity);
    T deleteActivity(String id);
}
