package com.hamropatro.controller;

import com.hamropatro.dto.StatsDTO;
import com.hamropatro.endpoint.StatsEndpoint;
import com.hamropatro.service.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatsController implements StatsEndpoint {

    @Autowired
    private StatsService statsService;

    @Override
   public ResponseEntity<StatsDTO> getStats() {
        StatsDTO stats = statsService.getStats();
        return ResponseEntity.ok(stats);
    }
}
