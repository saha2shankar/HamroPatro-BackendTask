package com.hamropatro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentListDTO {
    private Integer id;
    private String name;
    private String section;
    private Integer rollNumber;
    private String contactDetails;
    private String ClassName;  // Just a simple string from ClassSection
}
