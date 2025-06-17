package com.hamropatro.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hamropatro.dto.SubjectCreateDTO;
import com.hamropatro.dto.SubjectResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Subjects", description = "Subject APIs")
@RequestMapping("/api")
public interface SubjectEndpoint {
	
	@Operation(summary = "Add a new subject")
    @PostMapping("/addsubject")
    public ResponseEntity<?> addSubject(@RequestHeader(value = "X-Role", required = false) String role,@RequestBody SubjectCreateDTO dto);
	
	@Operation(summary = "List all subjects")
    @GetMapping("/subjectlist")
    public ResponseEntity<?> listSubjects();
   

}
