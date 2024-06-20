package ru.job4j.socialmediaapi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.socialmediaapi.model.Friend;
import ru.job4j.socialmediaapi.model.User;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FriendRepositoryTest {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void createUsers() {
        var userMain = new User();
        userMain.setLogin("main_user");
        userMain.setPassword("password");
        userMain.setEmail("email");
        var userFriend = new User();
        userFriend.setLogin("friend_user");
        userFriend.setPassword("password");
        userFriend.setEmail("email");
        userRepository.save(userMain);
        userRepository.save(userFriend);
    }

    @BeforeEach
    public void setUp() {
        friendRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenSaveFriend() {
        var friend = new Friend();
        var userMain = userRepository.findById(1).get();
        var userFriend = userRepository.findById(2).get();
        friend.setUserMain(userMain);
        friend.setUserFriend(userFriend);
        friend.setStatus("friend");
        friendRepository.save(friend);
        var foundFriend = friendRepository.findById(friend.getId());
        assertThat(foundFriend).isPresent();
        assertThat(foundFriend.get().getUserMain()).isEqualTo(userMain);
        assertThat(foundFriend.get().getUserFriend()).isEqualTo(userFriend);
    }
}