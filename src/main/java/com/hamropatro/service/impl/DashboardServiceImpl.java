package com.hamropatro.service.impl;
import com.hamropatro.dto.DashboardClassDTO;

import com.hamropatro.dto.SubjectTeacherDTO;
import com.hamropatro.model.ClassSection;
import com.hamropatro.repository.ClassSectionRepository;
import com.hamropatro.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ClassSectionRepository classSectionRepository;

    @Override
    public List<Map<String, DashboardClassDTO>> getDashboard() {
        List<ClassSection> classes = classSectionRepository.findAll();

        List<Map<String, DashboardClassDTO>> dashboardList = new ArrayList<>();

        for (ClassSection cls : classes) {
            DashboardClassDTO dto = new DashboardClassDTO();
            dto.setClassName(cls.getClassName());

            // Get subjects and teacher names from assignments
            List<SubjectTeacherDTO> subjects = cls.getTeachingAssignments().stream().map(ta -> {
                SubjectTeacherDTO stDto = new SubjectTeacherDTO();
                stDto.setSubject(ta.getSubject().getName());
                stDto.setTeacher(ta.getTeacher().getName());
                return stDto;
            }).collect(Collectors.toList());

            dto.setSubjects(subjects);

            // Total students in this class
            int totalStudents = (cls.getStudents() != null) ? cls.getStudents().size() : 0;
            dto.setTotalStudents(totalStudents);

            // Key is className + "_id"
            Map<String, DashboardClassDTO> map = new HashMap<>();
            map.put(cls.getClassName() + "_id", dto);

            dashboardList.add(map);
        }

        return dashboardList;
    }
}
