// File: com/hamropatro/controller/ClassSectionController.java

package com.hamropatro.controller;

import com.hamropatro.config.CommonUtil;
import com.hamropatro.dto.ClassSectionCreateDTO;
import com.hamropatro.dto.ClassSectionResponseDTO;
import com.hamropatro.endpoint.ClassSectionEndpoint;
import com.hamropatro.service.ClassandSectionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassSectionController implements ClassSectionEndpoint {

    @Autowired
    private ClassandSectionService classandSectionService;
    
    @Override
    public ResponseEntity<?> createClass(@RequestHeader(value = "X-Role", required = false) String role, @RequestBody ClassSectionCreateDTO dto) {
        if (role == null || !role.equalsIgnoreCase("teacher")) {
    		return CommonUtil.createErrorResponseMessage("Only teachers are allowed to create classes.", HttpStatus.FORBIDDEN);
 }
        boolean ok = classandSectionService.createClass(dto);
        if (ok) {
            return CommonUtil.createBuildResponseMessage(
                "Class section created successfully", HttpStatus.CREATED);
        } else {
            return CommonUtil.createErrorResponseMessage(
                "Failed to create class section", HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<?> getAllClasses() {
        List<ClassSectionResponseDTO> classes = classandSectionService.getAllClasses();
        if (classes.isEmpty()) {
            return CommonUtil.createErrorResponseMessage("No classes found", HttpStatus.NO_CONTENT);
        }
        return CommonUtil.createBuildResponse(classes, HttpStatus.OK);
    }

}
