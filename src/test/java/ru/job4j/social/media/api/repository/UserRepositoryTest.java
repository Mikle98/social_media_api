package ru.job4j.social.media.api.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.social.media.api.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
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
}