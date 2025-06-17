package com.hamropatro.service;

import java.util.List;

import com.hamropatro.dto.SubjectCreateDTO;
import com.hamropatro.dto.SubjectResponseDTO;

public interface SubjectService {
	
	  SubjectResponseDTO addSubject(SubjectCreateDTO dto);
	    List<SubjectResponseDTO> getAllSubjects();
	

}
