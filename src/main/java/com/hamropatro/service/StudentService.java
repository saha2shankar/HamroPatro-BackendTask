package com.hamropatro.service;

import java.util.List;
import java.util.Map;

import com.hamropatro.dto.StudentCreateDTO;
import com.hamropatro.dto.StudentFilterDTO;
import com.hamropatro.dto.StudentListDTO;
import com.hamropatro.dto.StudentUpdateDTO;
import com.hamropatro.exception.ResourcesNotFoundException;
import com.hamropatro.model.Student;

public interface StudentService {
    public Boolean createStudent(StudentCreateDTO dto);
    public List<StudentListDTO> getAllStudents();
    public List<StudentListDTO> getStudentsByClassName(String className);
    public List<StudentListDTO> getStudentsBySection(String section);
    public List<StudentListDTO> getStudentsByTeacherId(Integer teacherId);
    public StudentListDTO getStudentById(Integer id) throws Exception;
    public boolean updateStudent(StudentUpdateDTO dto) throws Exception;
    public StudentListDTO partialUpdateStudent(Integer id, Map<String, Object> updates) throws Exception;
    public boolean deleteStudentById(Integer id) throws Exception;



    




	

}
