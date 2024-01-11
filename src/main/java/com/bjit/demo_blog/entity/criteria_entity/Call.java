package com.bjit.demo_blog.entity.criteria_entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "phone_call")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @Column(name = "call_timestamp")
    private Date timestamp;

    @Column(name = "duration")
    private int duration;
}
