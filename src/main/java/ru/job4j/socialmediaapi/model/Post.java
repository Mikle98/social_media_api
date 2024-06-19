package ru.job4j.socialmediaapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
