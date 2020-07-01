package com.example.demo.service.impl;

import com.example.demo.entity.ActionType;
import com.example.demo.entity.Activity;
import com.example.demo.entity.ChangeSet;
import com.example.demo.entity.History;
import com.example.demo.exception.ActivityManagementException;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.HistoryRepository;
import com.example.demo.service.ActivityService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.SneakyThrows;
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

    @Transactional
    public Activity addMember(Activity activity) {
        if (activity.getId() != null) {
            activityRepository.findById(activity.getId()).orElseThrow(ActivityManagementException::new);
        }
        Activity savedActivity = activityRepository.insert(activity);
        List<ChangeSet> changeSet = Stream.of(Activity.class.getDeclaredFields())
                                          .map(field ->
                                               {
                                                   try {
                                                       field.setAccessible(true);
                                                       return new ChangeSet(field.getName(), "", field.get(savedActivity).toString());
                                                   } catch (IllegalAccessException e) {
                                                       log.error(e.getLocalizedMessage());
                                                       return new ChangeSet();
                                                   }
                                               }
                                          ).collect(Collectors.toList());
        History history = new History(savedActivity.getId(), LocalDateTime.now(), ActionType.CREATE, changeSet);
        historyRepository.insert(history);
        return savedActivity;
    }

    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    public Optional<Activity> findById(String id) {
        return activityRepository.findById(id);
    }
}
