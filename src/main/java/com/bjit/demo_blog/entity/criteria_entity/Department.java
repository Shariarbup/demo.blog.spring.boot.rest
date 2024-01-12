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
@Table(name = "department_table")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private long id;

    @Column(name = "dept_name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "dept_location", nullable = false, length = 100)
    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();
}
