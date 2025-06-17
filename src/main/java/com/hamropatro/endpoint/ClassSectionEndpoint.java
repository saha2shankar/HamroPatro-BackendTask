package com.hamropatro.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hamropatro.dto.ClassSectionCreateDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api")
@Tag(name = "Classes", description = "Class APIs")
public interface ClassSectionEndpoint {
	
	@Operation(summary = "Create a new classes")
    @PostMapping("/createclass")
    public ResponseEntity<?> createClass(@RequestHeader(value = "X-Role", required = false) String role, @RequestBody ClassSectionCreateDTO dto);
	
	@Operation(summary = "Get all classes")
    @GetMapping("/classes")
    public ResponseEntity<?> getAllClasses();

}
