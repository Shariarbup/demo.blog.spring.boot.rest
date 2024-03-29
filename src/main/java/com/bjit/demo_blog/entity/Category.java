package com.bjit.demo_blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(name = "title", length = 100, nullable = false)
    private String categoryTitle;
    @Column(name = "description")
    private String categoryDescription;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // jodi parent delete kori taile child o delete hoye jabe
    private List<Post> posts = new ArrayList<>();
}
