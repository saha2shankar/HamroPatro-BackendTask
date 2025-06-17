package com.hamropatro.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatsDTO {
    private long totalTeachers;
    private long totalStudents;
    private double averageClassSize;
    private String mostPopularSubject;
}
