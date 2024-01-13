package com.bjit.demo_blog.entity.criteria_entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class EmployeeStatisticsInfo {
    private Long totalNoOfEmployees;
    private Long totalDistinctNoOfEmployees;
    private Double maxSalaryOfEmployee;
    private Double averageSalaryOfEmployees;
    private Double sumOfSalaryOfEmployees;

}
