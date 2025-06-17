package com.hamropatro.dto;

import java.util.List;

import lombok.Data;

@Data
public class DashboardClassDTO {

	private String className;
    private List<SubjectTeacherDTO> subjects;
    private int totalStudents;
}
