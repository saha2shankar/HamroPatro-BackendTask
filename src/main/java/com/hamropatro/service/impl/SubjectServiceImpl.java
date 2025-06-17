package com.hamropatro.service.impl;

import com.hamropatro.dto.SubjectCreateDTO;
import com.hamropatro.dto.SubjectResponseDTO;
import com.hamropatro.model.Subject;
import com.hamropatro.repository.SubjectRepository;
import com.hamropatro.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectResponseDTO addSubject(SubjectCreateDTO dto) {
        Subject subject = new Subject();
        subject.setName(dto.getName());

        Subject saved = subjectRepository.save(subject);

        SubjectResponseDTO responseDTO = new SubjectResponseDTO();
        responseDTO.setId(saved.getId());
        responseDTO.setName(saved.getName());

        return responseDTO;
    }

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        return subjectRepository.findAll().stream().map(sub -> {
            SubjectResponseDTO dto = new SubjectResponseDTO();
            dto.setId(sub.getId());
            dto.setName(sub.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
