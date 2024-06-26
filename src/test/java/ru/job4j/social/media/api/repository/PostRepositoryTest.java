package ru.job4j.social.media.api.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.social.media.api.model.Post;
import ru.job4j.social.media.api.model.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setUp() {
        postRepository.deleteAll();
        userRepository.deleteAll();
        this.user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("email");
        userRepository.save(user);
    }

    @Test
    public void whenAddPostThenFindID() {
        var post = new Post();
        post.setTitle("title");
        post.setDescription("description");
        post.setUserId(user);
        postRepository.save(post);
        var found = postRepository.findById(post.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).contains("title");
    }

    @Test
    public void whenFindAll() {
        var post = new Post();
        post.setTitle("title");
        post.setDescription("description");
        post.setUserId(user);
        postRepository.save(post);
        var posts = postRepository.findAll();
        assertThat(posts).hasSize(1);
        assertThat(posts).extracting(Post::getTitle).contains("title");
    }
}