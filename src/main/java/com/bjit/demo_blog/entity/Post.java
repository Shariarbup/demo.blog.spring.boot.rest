package com.bjit.demo_blog.entity;

import com.bjit.demo_blog.custom_validator.ImageNameValid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(name = "post_title", length = 100, nullable = false)
    private String title;
    @Column(length = 1000)
    private String content;
    @ImageNameValid
    private String imageName;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL) // amder j foreign key hobe sheita shudhu comment table ei thakbe
    private Set<Comment> comments = new HashSet<>();

}
