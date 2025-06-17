package com.hamropatro.service;

import java.util.List;
import java.util.Map;

import com.hamropatro.dto.DashboardClassDTO;

public interface DashboardService {
    List<Map<String, DashboardClassDTO>> getDashboard();

}
