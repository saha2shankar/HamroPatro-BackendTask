package com.hamropatro.service.impl;

import com.hamropatro.dto.StatsDTO;
import com.hamropatro.model.ClassSection;
import com.hamropatro.model.TeachingAssignment;
import com.hamropatro.repository.ClassSectionRepository;
import com.hamropatro.repository.StudentRepository;
import com.hamropatro.repository.TeacherRepository;
import com.hamropatro.service.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassSectionRepository classSectionRepository;

    @Override
    public StatsDTO getStats() {
        long totalTeachers = teacherRepository.count();
        long totalStudents = studentRepository.count();

        // average class size
        List<ClassSection> classes = classSectionRepository.findAll();
        double averageClassSize = classes.isEmpty() ?
                0.0 :
                classes.stream()
                       .mapToInt(c -> c.getStudents().size())
                       .average()
                       .orElse(0.0);

        // most popular subject = subject name with highest assignment count
        Map<String, Long> subjectCounts = classes.stream()
            .flatMap(c -> c.getTeachingAssignments().stream())
            .map(TeachingAssignment::getSubject)
            .filter(Objects::nonNull)
            .map(s -> s.getName())
            .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        String mostPopularSubject = subjectCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("N/A");

        return StatsDTO.builder()
                .totalTeachers(totalTeachers)
                .totalStudents(totalStudents)
                .averageClassSize(averageClassSize)
                .mostPopularSubject(mostPopularSubject)
                .build();
    }
}
