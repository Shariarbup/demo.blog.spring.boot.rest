package com.bjit.demo_blog.entity.criteria_entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Partner")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Version
    @Column(name = "version")
    private int version;
}
