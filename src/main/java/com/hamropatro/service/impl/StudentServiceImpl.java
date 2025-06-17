package com.hamropatro.service.impl;

import java.util.List;


import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamropatro.dto.StudentCreateDTO;
import com.hamropatro.dto.StudentListDTO;
import com.hamropatro.dto.StudentUpdateDTO;
import com.hamropatro.exception.ResourcesNotFoundException;
import com.hamropatro.model.ClassSection;
import com.hamropatro.model.Student;
import com.hamropatro.repository.ClassSectionRepository;
import com.hamropatro.repository.StudentRepository;
import com.hamropatro.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ClassSectionRepository classSectionRepository;

	@Override
	public Boolean createStudent(StudentCreateDTO dto) {
	    Optional<ClassSection> sectionOpt = classSectionRepository.findById(dto.getClassSectionId());
	    if (sectionOpt.isEmpty()) {
	        return false;
	    }

	    Student student = new Student();
	    student.setName(dto.getName());
	    student.setSection(dto.getSection());
	    student.setRollNumber(dto.getRollNumber());
	    student.setContactDetails(dto.getContactDetails());
	    student.setClassNumber(sectionOpt.get());

	    studentRepository.save(student);
	    return true;
	}
	
	@Override
	public List<StudentListDTO> getAllStudents() {
	    List<Student> students = studentRepository.findAll();

	    return students.stream().map(student -> {
	        StudentListDTO dto = new StudentListDTO();
	        dto.setId(student.getId());
	        dto.setName(student.getName());
	        dto.setSection(student.getSection());
	        dto.setRollNumber(student.getRollNumber());
	        dto.setContactDetails(student.getContactDetails());

	        if (student.getClassNumber() != null) {
	            dto.setClassName(student.getClassNumber().getClassName());
	        }

	        return dto;
	    }).collect(Collectors.toList());
	}


    private StudentListDTO mapToDTO(Student student) {
        return StudentListDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .section(student.getSection())
                .rollNumber(student.getRollNumber())
                .contactDetails(student.getContactDetails())
                .ClassName(student.getClassNumber().getClassName())
                .build();
    }

    @Override
    public List<StudentListDTO> getStudentsByClassName(String className) {
        return studentRepository.findByClassNumber_ClassName(className)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentListDTO> getStudentsBySection(String section) {
        return studentRepository.findBySection(section)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentListDTO> getStudentsByTeacherId(Integer teacherId) {
        return studentRepository.findByClassNumber_TeachingAssignments_Teacher_Id(teacherId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    
    @Override
    public StudentListDTO getStudentById(Integer id) throws Exception {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Student not found with ID: " + id));
        return mapToDTO(student);
    }
    
    @Override
    public boolean updateStudent(StudentUpdateDTO dto) throws Exception {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Student or ID cannot be null for update.");
        }

        Optional<Student> existingOpt = studentRepository.findById(dto.getId());
        if (existingOpt.isEmpty()) {
            return false;
        }

        Student student = existingOpt.get();

        student.setName(dto.getName());
        student.setSection(dto.getSection());
        student.setRollNumber(dto.getRollNumber());
        student.setContactDetails(dto.getContactDetails());

        if (dto.getClassSectionId() != null) {
            ClassSection section = classSectionRepository.findById(dto.getClassSectionId())
                    .orElseThrow(() -> new ResourcesNotFoundException("ClassSection not found with ID = " + dto.getClassSectionId()));
            student.setClassNumber(section);
        }

        studentRepository.save(student);
        return true;
    }
    
    @Override
    public StudentListDTO partialUpdateStudent(Integer id, Map<String, Object> updates) throws Exception {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Student not found with id = " + id));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Student.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, student, value);
            }
        });

        studentRepository.save(student);
        return mapToDTO(student); // convert updated entity to DTO
    }
    
    @Override
    public boolean deleteStudentById(Integer id) throws ResourcesNotFoundException {
        // Check existence first
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourcesNotFoundException("Student not found with id = " + id));
        
        studentRepository.delete(student);
        return true;
    }





}
