package com.hamropatro.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponseDTO {
    private Integer id;
    private String name;
    private String contactDetails;
    private List<AssignmentResponseDTO> classes;

    // Getters & Setters
}
