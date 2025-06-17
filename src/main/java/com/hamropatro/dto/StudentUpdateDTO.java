package com.hamropatro.dto;

import lombok.Data;

@Data
public class StudentUpdateDTO {
    private Integer id;
    private String name;
    private String section;
    private Integer rollNumber;
    private String contactDetails;
    private Integer classSectionId;
}
