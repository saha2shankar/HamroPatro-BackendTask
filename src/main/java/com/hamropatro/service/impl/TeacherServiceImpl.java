package com.hamropatro.service.impl;

import com.hamropatro.config.CommonUtil;

import com.hamropatro.dto.AssignmentResponseDTO;
import com.hamropatro.dto.TeacherDTO;
import com.hamropatro.dto.TeacherResponseDTO;
import com.hamropatro.exception.ResourcesNotFoundException;
import com.hamropatro.model.Teacher;
import com.hamropatro.model.TeachingAssignment;
import com.hamropatro.repository.TeacherRepository;
import com.hamropatro.repository.SubjectRepository;
import com.hamropatro.repository.ClassSectionRepository;
import com.hamropatro.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private ClassSectionRepository classSectionRepository;

	@Override
	public ResponseEntity<?> saveTeacher(TeacherDTO dto) {
		try {
			Teacher teacher = new Teacher();
			teacher.setName(dto.getName());
			teacher.setContactDetails(dto.getContactDetails());

			List<TeachingAssignment> assignments = dto.getClasses().stream().map(a -> {
				TeachingAssignment ta = new TeachingAssignment();
				ta.setTeacher(teacher);
				ta.setSubject(subjectRepository.findById(a.getSubjectId())
						.orElseThrow(() -> new RuntimeException("Subject not found with ID: " + a.getSubjectId())));
				ta.setClassSection(classSectionRepository.findById(a.getClassSectionId()).orElseThrow(
						() -> new RuntimeException("Class section not found with ID: " + a.getClassSectionId())));
				return ta;
			}).collect(Collectors.toList());

			teacher.setAssignments(assignments);
			teacherRepository.save(teacher);
			return CommonUtil.createBuildResponseMessage("Teacher saved successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return CommonUtil.createErrorResponseMessage("Teacher Save failed !", HttpStatus.BAD_REQUEST);

		}
	}

	@Override
	public List<TeacherResponseDTO> getAllTeachers() {
	    List<Teacher> teachers = teacherRepository.findAll();
	    return teachers.stream().map(teacher -> {
	        TeacherResponseDTO dto = new TeacherResponseDTO();
	        dto.setId(teacher.getId());
	        dto.setName(teacher.getName());
	        dto.setContactDetails(teacher.getContactDetails());

	        List<AssignmentResponseDTO> assignments = teacher.getAssignments().stream().map(assignment -> {
	            AssignmentResponseDTO aDto = new AssignmentResponseDTO();
	            aDto.setSubjectName(assignment.getSubject().getName());
	            aDto.setClassSectionId(assignment.getClassSection().getId());
	            aDto.setClassSectionName(assignment.getClassSection().getClassName());
	            return aDto;
	        }).toList();

	        dto.setClasses(assignments);
	        return dto;
	    }).toList();
	}

	@Override
	public TeacherResponseDTO getTeacherById(Integer id) throws ResourcesNotFoundException {
	    Teacher teacher = teacherRepository.findById(id)
	        .orElseThrow(() -> new ResourcesNotFoundException("Teacher not found with id = " + id));

	    TeacherResponseDTO dto = new TeacherResponseDTO();
	    dto.setId(teacher.getId());
	    dto.setName(teacher.getName());
	    dto.setContactDetails(teacher.getContactDetails());

	    List<AssignmentResponseDTO> assignments = teacher.getAssignments().stream().map(assignment -> {
	        AssignmentResponseDTO aDto = new AssignmentResponseDTO();
	        aDto.setSubjectName(assignment.getSubject().getName());
	        aDto.setClassSectionId(assignment.getClassSection().getId());
	        aDto.setClassSectionName(assignment.getClassSection().getClassName());
	        return aDto;
	    }).toList();

	    dto.setClasses(assignments);
	    return dto;
	}


}
