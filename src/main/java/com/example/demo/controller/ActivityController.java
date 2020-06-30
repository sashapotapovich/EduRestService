package com.example.demo.controller;

import com.example.demo.entity.Activity;
import com.example.demo.service.ActivityService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
    
    
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/addMember", produces = {
            MediaType.APPLICATION_JSON_VALUE
    }, consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<?> addJusticeLeagueMember(@Valid @RequestBody
                                                            Activity justiceLeagueMember) {
        try {
            activityService.addMember(justiceLeagueMember);
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Activity was created", HttpStatus.CREATED);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Activity> getAllUsers() {
        System.out.println("Getting Activities");
        return activityService.findAll();
    }
}