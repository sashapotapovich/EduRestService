package com.example.demo.service.impl;

import com.example.demo.entity.ActionType;
import com.example.demo.entity.Activity;
import com.example.demo.entity.ChangeSet;
import com.example.demo.entity.History;
import com.example.demo.exception.ActivityManagementException;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.ActivityService;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService<Activity> {

    private final ActivityRepository activityRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    private ActivityServiceImpl(ActivityRepository activityRepository, HistoryRepository historyRepository) {
        this.activityRepository = activityRepository;
        this.historyRepository = historyRepository;
    }


    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Activity findById(String id) {
        return activityRepository.findById(id).orElseThrow(
                () -> new ActivityManagementException("Activity was not found, id - " + id));
    }

    @Transactional
    public Activity addActivity(Activity activity) {
        if (activity.getId() != null) {
            activityRepository.findById(activity.getId()).orElseThrow(ActivityManagementException::new);
        }
        log.debug("New Activity - {}", activity);
        truncateTime(activity);
        Activity savedActivity = activityRepository.insert(activity);
        List<ChangeSet> changeSet = new ArrayList<>();
        Field[] declaredFields = Activity.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                declaredField.setAccessible(true);
                changeSet.add(new ChangeSet(declaredField.getName(),
                                            "",
                                            declaredField.get(savedActivity).toString()));
            } catch (IllegalAccessException e) {
                log.error(e.getLocalizedMessage());
            }
        }
        History history = new History(savedActivity.getId(),
                                      LocalDateTime.now(),
                                      ActionType.CREATE, changeSet);
        historyRepository.insert(history);
        return savedActivity;
    }

    @Transactional
    public Activity update(String id, Activity activity) {
        Activity byId = activityRepository.findById(id).orElseThrow(
                () -> new ActivityManagementException("Activity was not found, id - " + id));
        activity.setId(byId.getId());
        truncateTime(activity);
        activityRepository.save(activity);
        List<ChangeSet> changeSet = new ArrayList<>();
        Field[] declaredFields = Activity.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                declaredField.setAccessible(true);
                if (!declaredField.get(byId).equals(declaredField.get(activity))) {
                    changeSet.add(new ChangeSet(declaredField.getName(),
                                                declaredField.get(byId).toString(),
                                                declaredField.get(activity).toString()));
                }
            } catch (IllegalAccessException e) {
                log.error(e.getLocalizedMessage());
            }
        }
        History history = new History(activity.getId(),
                                      LocalDateTime.now(),
                                      ActionType.UPDATE, changeSet);
        historyRepository.insert(history);
        return activity;
    }

    @Transactional
    public Activity deleteActivity(String id) {
        Activity byId = activityRepository.findById(id).orElseThrow(
                () -> new ActivityManagementException("Activity was not found, id - " + id));
        activityRepository.delete(byId);
        List<ChangeSet> changeSet = new ArrayList<>();
        Field[] declaredFields = Activity.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                declaredField.setAccessible(true);
                changeSet.add(new ChangeSet(declaredField.getName(),
                                            declaredField.get(byId).toString(),
                                            ""));
            } catch (IllegalAccessException e) {
                log.error(e.getLocalizedMessage());
            }
        }
        History history = new History(byId.getId(),
                                      LocalDateTime.now(),
                                      ActionType.DELETE, changeSet);
        historyRepository.insert(history);
        return byId;
    }

    private void truncateTime(Activity activity) {
        activity.setStartDateTime(activity.getStartDateTime().truncatedTo(ChronoUnit.MILLIS));
        activity.setEndDateTime(activity.getEndDateTime().truncatedTo(ChronoUnit.MILLIS));
    }
}
