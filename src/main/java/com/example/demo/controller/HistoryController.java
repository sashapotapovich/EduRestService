package com.example.demo.controller;

import com.example.demo.entity.History;
import com.example.demo.exception.HistoryNotFoundException;
import com.example.demo.repository.HistoryRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryRepository historyRepository;

    public HistoryController(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @GetMapping
    public List<History> getAllHistoryRecords() {
        return historyRepository.findAll();
    }

    @GetMapping("/{id}")
    public History getHistoryForId(@PathVariable String id) {
        return historyRepository.findById(id).orElseThrow(
                () -> new HistoryNotFoundException("History records were not found for the id - " + id)
        );
    }
}
