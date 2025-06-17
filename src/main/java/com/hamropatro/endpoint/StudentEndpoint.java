package com.hamropatro.endpoint;

import java.util.List;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hamropatro.dto.StudentCreateDTO;
import com.hamropatro.dto.StudentListDTO;
import com.hamropatro.dto.StudentUpdateDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Students", description = "All the Students operations APIs")
@RequestMapping("/api")
public interface StudentEndpoint {

	@PostMapping("/students")
	@Operation(summary = "Save student by teacher")
	public ResponseEntity<?> saveStudent(@RequestHeader(value = "X-Role", required = false) String role,@RequestBody StudentCreateDTO studentDto);

	@GetMapping("/students")
	@Operation(summary = "List all students")
	public ResponseEntity<?> getAllStudents();

	@Operation(summary = "List of Students (optional filters: className, section, teacherId)")
	@GetMapping("/filter")
	public ResponseEntity<List<StudentListDTO>> studentListFilter(@RequestParam(required = false) String className,
			@RequestParam(required = false) String section, @RequestParam(required = false) Integer teacherId);

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentListDTO> getStudentById(@PathVariable Integer id) throws Exception;

	@PutMapping("/students")
	@Operation(summary = "Update an existing student (Full Update)", description = "Teachers can update all details of an existing student. The entire student object must be provided.")
	public ResponseEntity<?> updateStudent(@RequestHeader(value = "X-Role", required = false) String role, @RequestBody StudentUpdateDTO dto);

	@PatchMapping("/students/{id}")
	public ResponseEntity<?> partialUpdateStudent(@RequestHeader(value = "X-Role", required = false) String role, @PathVariable Integer id, @RequestBody Map<String, Object> updates);

	@Operation(summary = "Delete a student by ID")
	@DeleteMapping("/students{id}")
	public ResponseEntity<?> deleteStudent(@RequestHeader(value = "X-Role", required = false) String role, @PathVariable Integer id);
}
