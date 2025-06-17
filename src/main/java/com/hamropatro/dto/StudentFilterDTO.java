package com.hamropatro.dto;

import lombok.Data;

@Data
public class StudentFilterDTO {

    private String classNumber;
    private String section;
    private Integer teacherId;
}
