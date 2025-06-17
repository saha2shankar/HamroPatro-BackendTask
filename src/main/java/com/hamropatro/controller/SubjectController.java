package com.hamropatro.controller;

import com.hamropatro.dto.SubjectCreateDTO;
import com.hamropatro.dto.SubjectResponseDTO;
import com.hamropatro.endpoint.SubjectEndpoint;
import com.hamropatro.service.SubjectService;
import com.hamropatro.config.CommonUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController implements SubjectEndpoint {

    @Autowired
    private SubjectService subjectService;

    @Override
    public ResponseEntity<?> addSubject(@RequestHeader(value = "X-Role", required = false) String role, @RequestBody SubjectCreateDTO dto) {
    	if (role == null || !role.equalsIgnoreCase("teacher")) {
    		return CommonUtil.createErrorResponseMessage("Only teachers are allowed to add new subjects.", HttpStatus.FORBIDDEN);
 }
        SubjectResponseDTO subject = subjectService.addSubject(dto);
        
		return CommonUtil.createBuildResponse("Subject added success", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> listSubjects() {
        List<SubjectResponseDTO> subjects = subjectService.getAllSubjects();
        if (subjects.isEmpty()) {
            return CommonUtil.createErrorResponseMessage("No subjects found", HttpStatus.NO_CONTENT);
        }
        return CommonUtil.createBuildResponse(subjects, HttpStatus.OK);
    }
}
