package com.bloom.backend.community;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pseudonym;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String category;

    private Integer likes = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
}
