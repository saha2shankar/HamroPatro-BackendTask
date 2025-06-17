package com.hamropatro.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hamropatro.dto.StatsDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api")
@Tag(name = "Stats", description = "Aggregate statistics")
public interface StatsEndpoint {

	@GetMapping("/stats")
    @Operation(summary = "Get aggregate statistics: total teachers, students, avg class size, most popular subject")
    public ResponseEntity<StatsDTO> getStats();
}
