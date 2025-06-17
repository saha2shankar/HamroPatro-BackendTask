package com.hamropatro.controller;

import com.hamropatro.config.CommonUtil;

import com.hamropatro.dto.TeacherDTO;
import com.hamropatro.dto.TeacherResponseDTO;
import com.hamropatro.endpoint.TeacherEndpoint;
import com.hamropatro.exception.ResourcesNotFoundException;
import com.hamropatro.service.TeacherService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController implements TeacherEndpoint {

    @Autowired
    private TeacherService teacherService;

    @Override
    public ResponseEntity<?> saveTeacher(@RequestHeader(value = "X-Role", required = false) String role, @Valid @RequestBody TeacherDTO dto) {
    	if (role == null || !role.equalsIgnoreCase("teacher")) {
    		return CommonUtil.createErrorResponseMessage("Only teachers are allowed to save new teachers.", HttpStatus.FORBIDDEN);
            
        }
        return teacherService.saveTeacher(dto);
    }
    

    @Override
    public ResponseEntity<?> getTeacherList() {
        List<TeacherResponseDTO> allTeachers = teacherService.getAllTeachers();
        if (CollectionUtils.isEmpty(allTeachers)) {
            return ResponseEntity.noContent().build();
        } else {
            return CommonUtil.createBuildResponse(allTeachers, HttpStatus.OK);
        }
    }
    

    @Override
    public ResponseEntity<?> getTeacherById(@PathVariable Integer id) {
        try {
            TeacherResponseDTO teacher = teacherService.getTeacherById(id);
            return CommonUtil.createBuildResponse(teacher, HttpStatus.OK);

        } catch (ResourcesNotFoundException e) {
            return CommonUtil.createErrorResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return CommonUtil.createErrorResponseMessage("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
