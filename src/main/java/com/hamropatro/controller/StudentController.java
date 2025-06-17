package com.hamropatro.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hamropatro.config.CommonUtil;
import com.hamropatro.dto.StudentCreateDTO;
import com.hamropatro.dto.StudentListDTO;
import com.hamropatro.dto.StudentUpdateDTO;
import com.hamropatro.endpoint.StudentEndpoint;
import com.hamropatro.exception.ResourcesNotFoundException;
import com.hamropatro.service.StudentService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class StudentController implements StudentEndpoint {

	@Autowired
	private StudentService studentService;

	@Override
	public ResponseEntity<?> saveStudent(@RequestHeader(value = "X-Role", required = false) String role,
			@RequestBody StudentCreateDTO studentDto) {
		if (role == null || !role.equalsIgnoreCase("teacher")) {
			return CommonUtil.createErrorResponseMessage("Only teachers are allowed to add new Students.",
					HttpStatus.FORBIDDEN);
		}
		Boolean success = studentService.createStudent(studentDto);
		if (success) {
			return CommonUtil.createBuildResponseMessage("Student Saved successfully!", HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorResponseMessage("Student Save failed!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getAllStudents() {
		List<StudentListDTO> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}

	@Override
	public ResponseEntity<List<StudentListDTO>> studentListFilter(@RequestParam(required = false) String className,
			@RequestParam(required = false) String section, @RequestParam(required = false) Integer teacherId) {

		List<StudentListDTO> students;

		if (section != null) {
			students = studentService.getStudentsBySection(section);
		} else if (className != null) {
			students = studentService.getStudentsByClassName(className);
		} else if (teacherId != null) {
			students = studentService.getStudentsByTeacherId(teacherId);
		} else {
			students = studentService.getAllStudents(); // DTO-based
		}

		if (CollectionUtils.isEmpty(students)) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(students);
	}

	@Override
	public ResponseEntity<StudentListDTO> getStudentById(@PathVariable Integer id) throws Exception {
		StudentListDTO student = studentService.getStudentById(id);
		return ResponseEntity.ok(student);
	}

	@Override
	public ResponseEntity<?> updateStudent(@RequestHeader(value = "X-Role", required = false) String role,
			@RequestBody StudentUpdateDTO dto) {
		if (role == null || !role.equalsIgnoreCase("teacher")) {
			return CommonUtil.createErrorResponseMessage("Only teachers are allowed to update student details.",
					HttpStatus.FORBIDDEN);
		}

		if (dto == null || dto.getId() == null) {
			return ResponseEntity.badRequest().body("Invalid student data. ID is required for update.");
		}

		try {
			boolean updated = studentService.updateStudent(dto);
			if (updated) {
				return ResponseEntity.ok("Student updated successfully!");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found for update.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
		}
	}

	@Override
	public ResponseEntity<?> partialUpdateStudent(@RequestHeader(value = "X-Role", required = false) String role,
			@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
		if (role == null || !role.equalsIgnoreCase("teacher")) {
			return CommonUtil.createErrorResponseMessage("Only teachers are allowed to update student details.",
					HttpStatus.FORBIDDEN);
		}
		try {
			StudentListDTO updatedStudent = studentService.partialUpdateStudent(id, updates);
			return ResponseEntity.ok(updatedStudent);
		} catch (ResourcesNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error during update: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteStudent(@RequestHeader(value = "X-Role", required = false) String role,
			@PathVariable Integer id) {
		if (role == null || !role.equalsIgnoreCase("teacher")) {
			return CommonUtil.createErrorResponseMessage("Only teachers are allowed to delete student details.",
					HttpStatus.FORBIDDEN);
		}
		try {
			studentService.deleteStudentById(id);
			// 204 No Content is idiomatic for successful deletes with no body
			return ResponseEntity.noContent().build();
		} catch (ResourcesNotFoundException e) {
			// 404 if not found
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("status", "failed", "message", e.getMessage()));
		} catch (Exception e) {
			// 500 for anything else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("status", "failed", "message", "Could not delete student: " + e.getMessage()));
		}
	}

}
