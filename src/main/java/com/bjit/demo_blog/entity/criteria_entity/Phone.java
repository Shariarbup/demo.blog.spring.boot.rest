package com.bjit.demo_blog.entity.criteria_entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Phone")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Phone {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "phone_number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType type;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> calls = new ArrayList<>( );
}

