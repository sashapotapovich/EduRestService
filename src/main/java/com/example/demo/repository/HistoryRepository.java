package com.example.demo.repository;

import com.example.demo.entity.Activity;
import com.example.demo.entity.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String> {
}
