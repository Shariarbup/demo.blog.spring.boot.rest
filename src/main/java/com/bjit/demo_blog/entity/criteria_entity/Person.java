package com.bjit.demo_blog.entity.criteria_entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "address")
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdOn")
    private Date createdOn;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();
}