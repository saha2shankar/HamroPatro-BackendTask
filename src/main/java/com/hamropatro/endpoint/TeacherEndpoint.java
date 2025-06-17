package com.hamropatro.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hamropatro.dto.TeacherDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RequestMapping("/api")
@Tag(name = "Teacher", description = "All the Teacher operations APIs")
public interface TeacherEndpoint {

	@PostMapping("/teachers")
	@Operation(summary = "Create a new teacher")
	public ResponseEntity<?> saveTeacher(@RequestHeader(value = "X-Role", required = false) String role,@Valid @RequestBody TeacherDTO dto);

	@GetMapping("/teachers")
	@Operation(summary = "List of Teachers (DTO-based)")
	public ResponseEntity<?> getTeacherList();

	@Operation(summary = "Find teacher by Id")
	@GetMapping("/teacher/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable Integer id);

}
