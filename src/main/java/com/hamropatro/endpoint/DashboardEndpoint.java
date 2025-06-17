package com.hamropatro.endpoint;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hamropatro.dto.DashboardClassDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Dashboard", description = "Dashboard APIs")
@RequestMapping("/api")
public interface DashboardEndpoint {
	

	@Operation(summary = "Dashboard")
    @GetMapping("/dashboard")
    public ResponseEntity<List<Map<String, DashboardClassDTO>>> getDashboard();

}
