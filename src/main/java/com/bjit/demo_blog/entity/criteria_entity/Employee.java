package com.bjit.demo_blog.entity.criteria_entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee_table")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "designation")
    private String designation;

    @Column(name = "salary")
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
}
