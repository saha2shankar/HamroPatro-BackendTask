package com.hamropatro.service;



import java.util.List;

import com.hamropatro.dto.ClassSectionCreateDTO;
import com.hamropatro.dto.ClassSectionResponseDTO;


public interface ClassandSectionService {
    public boolean createClass(ClassSectionCreateDTO dto);
    public List<ClassSectionResponseDTO> getAllClasses();



}
