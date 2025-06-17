package com.hamropatro.dto;

import lombok.Data;


@Data
public class StudentCreateDTO {

    private String name;
    private String classNumber;
    private String section;
    private Integer rollNumber;
    private String contactDetails;
    private Integer classSectionId;
}
