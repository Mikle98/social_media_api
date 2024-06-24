package ru.job4j.social.media.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_main")
    private User userMain;

    @ManyToOne
    @JoinColumn(name = "user_friend")
    private User userFriend;

    private String status;
}
