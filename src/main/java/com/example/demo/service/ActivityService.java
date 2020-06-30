package com.example.demo.service;

import com.example.demo.entity.Activity;
import java.util.List;

public interface ActivityService<T extends Activity> { //TODO: DO we need wildcard here
    T addMember(T activity);
    List<T>findAll();
}
