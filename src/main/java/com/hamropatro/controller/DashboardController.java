package com.hamropatro.controller;
import com.hamropatro.service.DashboardService;



import com.hamropatro.dto.DashboardClassDTO;
import com.hamropatro.endpoint.DashboardEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DashboardController implements DashboardEndpoint {

    @Autowired
    private DashboardService dashboardService;

    @Override
    public ResponseEntity<List<Map<String, DashboardClassDTO>>> getDashboard() {
        List<Map<String, DashboardClassDTO>> dashboard = dashboardService.getDashboard();
        if (dashboard.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dashboard);
    }
}
