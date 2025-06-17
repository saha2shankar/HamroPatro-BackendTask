package com.hamropatro.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentResponseDTO {
    private String subjectName;
    private Integer classSectionId;
    private String classSectionName;

    // Getters & Setters
}
