package ru.job4j.social.media.api.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.job4j.social.media.api.model.User;
import ru.job4j.social.media.api.SocialMediaApiApplication;

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration(classes = SocialMediaApiApplication.class)
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
    public void whenAddUserAndFind() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        userRepository.save(user);
        var userOptional = userRepository.findById(user.getId());
        assertThat(userOptional).isPresent();
        assertThat(userOptional.get().getLogin()).isEqualTo("login");
    }
}