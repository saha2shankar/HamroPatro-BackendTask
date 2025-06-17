package com.hamropatro.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
	
    private String name;
    private String contactDetails;
    private List<AssignmentDTO> classes;

}
