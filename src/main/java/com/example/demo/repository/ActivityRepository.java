package com.example.demo.repository;

import com.example.demo.entity.Activity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ActivityRepository extends MongoRepository<Activity, String> {

    Optional<Activity> findById(String Id);
}
