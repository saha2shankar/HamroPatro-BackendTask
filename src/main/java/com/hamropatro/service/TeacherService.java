package com.hamropatro.service;



import java.util.List;



import org.springframework.http.ResponseEntity;

import com.hamropatro.dto.TeacherDTO;
import com.hamropatro.dto.TeacherResponseDTO;
import com.hamropatro.exception.ResourcesNotFoundException;

public interface TeacherService {

    ResponseEntity<?> saveTeacher(TeacherDTO dto);
    List<TeacherResponseDTO> getAllTeachers();
    TeacherResponseDTO getTeacherById(Integer id) throws ResourcesNotFoundException;



    
}
