package com.hamropatro.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hamropatro.dto.ClassSectionCreateDTO;
import com.hamropatro.dto.ClassSectionResponseDTO;
import com.hamropatro.model.ClassSection;
import com.hamropatro.repository.ClassSectionRepository;
import com.hamropatro.service.ClassandSectionService;

@Service
public class ClassSectionServiceImpl implements ClassandSectionService {
	
	@Autowired
	private ClassSectionRepository  classSectionRepository;

	@Override
	public boolean createClass(ClassSectionCreateDTO dto) {
	    ClassSection cs = new ClassSection();
	    cs.setClassName(dto.getClassName());
	    classSectionRepository.save(cs);
	    return true;    // or return saved != null
	}
	
	@Override
	public List<ClassSectionResponseDTO> getAllClasses() {
	    List<ClassSection> classSections = classSectionRepository.findAll();
	    return classSections.stream().map(cs -> {
	        ClassSectionResponseDTO dto = new ClassSectionResponseDTO();
	        dto.setId(cs.getId());
	        dto.setClassName(cs.getClassName());
	        return dto;
	    }).collect(Collectors.toList());
	}



}
