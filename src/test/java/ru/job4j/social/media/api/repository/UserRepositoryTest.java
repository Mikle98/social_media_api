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
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @BeforeEach
    public void setUp() {
        friendRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenAddUserThenFindID() {
        var user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        userRepository.save(user);
        var found = userRepository.findById(user.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(user.getId());
    }

    @Test
    public void whenFindAllUser() {
        var user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        userRepository.save(user);
        var user2 = new User();
        user2.setLogin("login2");
        user2.setPassword("password2");
        user2.setEmail("email2");
        userRepository.save(user2);
        var found = userRepository.findAll();
        assertThat(found).hasSize(2);
        assertThat(found).extracting(User::getLogin).contains("login", "login2");
    }

    @Test
    public void whenFindUserByLoginAndPassword() {
        var user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        userRepository.save(user);
        var found = userRepository.findByUserWithHQL("login", "password");
        assertThat(found).isPresent();
        assertThat(found.get().getLogin()).isEqualTo("login");
    }

    @Test
    public void whenFindAllFollowers() {
        var user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        userRepository.save(user);
        var follower = new User();
        follower.setLogin("follower");
        follower.setPassword("password");
        follower.setEmail("email");
        userRepository.save(follower);
        var follower2 = new User();
        follower2.setLogin("follower2");
        follower2.setPassword("password2");
        follower2.setEmail("email2");
        userRepository.save(follower2);
        var friend = new Friend();
        friend.setToUser(user);
        friend.setFromUser(follower);
        friend.setStatus("follower");
        friendRepository.save(friend);
        var friend2 = new Friend();
        friend2.setToUser(user);
        friend2.setFromUser(follower2);
        friend2.setStatus("follower");
        friendRepository.save(friend2);
        var found = userRepository.findAllFollowers(user.getId());
        assertThat(found).hasSize(2);
        assertThat(found).extracting(User::getLogin).contains("follower", "follower2");
    }

    @Test
    public void whenFindAllFriends() {
        var user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        userRepository.save(user);
        var friend = new User();
        friend.setLogin("friend");
        friend.setPassword("password");
        friend.setEmail("email");
        userRepository.save(friend);
        var friend2 = new User();
        friend2.setLogin("friend2");
        friend2.setPassword("password2");
        friend2.setEmail("email2");
        userRepository.save(friend2);
        var friendTable = new Friend();
        friendTable.setToUser(user);
        friendTable.setFromUser(friend);
        friendTable.setStatus("friend");
        friendRepository.save(friendTable);
        var friendTable2 = new Friend();
        friendTable2.setToUser(user);
        friendTable2.setFromUser(friend2);
        friendTable2.setStatus("friend");
        friendRepository.save(friendTable2);
        var found = userRepository.findAllFriend(user.getId());
        assertThat(found).hasSize(2);
        assertThat(found).extracting(User::getLogin).contains("friend", "friend2");
    }
}