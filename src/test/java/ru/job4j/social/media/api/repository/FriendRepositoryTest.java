package ru.job4j.social.media.api.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.social.media.api.model.Friend;
import ru.job4j.social.media.api.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FriendRepositoryTest {
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;
    private User user;
    private User user2;

    @BeforeEach
    public void setUp() {
        friendRepository.deleteAll();
        userRepository.deleteAll();
        user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        userRepository.save(user);
        user2 = new User();
        user2.setLogin("login2");
        user2.setPassword("password2");
        user2.setEmail("email2");
        userRepository.save(user2);
    }

    @Test
    public void whenAddFriendThenFindId() {
        var friend = new Friend();
        friend.setUserMain(user);
        friend.setUserFriend(user2);
        friend.setStatus("friend");
        friendRepository.save(friend);
        var found = friendRepository.findById(friend.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(friend.getId());
    }

    @Test
    public void whenAllFind() {
        var friend = new Friend();
        friend.setUserMain(user);
        friend.setUserFriend(user2);
        friend.setStatus("friend");
        friendRepository.save(friend);
        var found = friendRepository.findAll();
        assertThat(found).hasSize(1);
        assertThat(found).extracting(Friend::getId).contains(friend.getId());
    }
}