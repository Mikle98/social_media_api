package ru.job4j.social.media.api.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import ru.job4j.social.media.api.model.File;
import ru.job4j.social.media.api.model.Friend;
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
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FriendRepository friendRepository;
    private User user;

    @BeforeEach
    public void setUp() {
        fileRepository.deleteAll();
        postRepository.deleteAll();
        friendRepository.deleteAll();
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

    @Test
    public void whenUpdateTitleAndDescriription() {
        var post = new Post();
        post.setTitle("title");
        post.setDescription("description");
        post.setUserId(user);
        postRepository.save(post);
        postRepository.updateTitleAndDescription(post.getId(), "title2", "description2");
        var found = postRepository.findById(post.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).contains("title2");
    }

    @Test
    public void whenDeleteImage() {
        var file = new File();
        file.setName("file");
        file.setPath("path");
        fileRepository.save(file);
        var post = new Post();
        post.setTitle("title");
        post.setDescription("description");
        post.setUserId(user);
        post.setFileId(file);
        postRepository.save(post);
        postRepository.deleteFile(post.getId());
        var found = postRepository.findById(post.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getFileId()).isNull();
    }

    @Test
    public void whenDeletePost() {
        var post = new Post();
        post.setTitle("title");
        post.setDescription("description");
        post.setUserId(user);
        postRepository.save(post);
        postRepository.deletePost(post.getId());
        var found = postRepository.findById(post.getId());
        assertThat(found).isEmpty();
    }

    /*@Test
    public void whenFindFollowerPosts() {
        var user2 = new User();
        user2.setLogin("login2");
        user2.setPassword("password2");
        user2.setEmail("email2");
        userRepository.save(user2);
        var user3 = new User();
        user3.setLogin("login3");
        user3.setPassword("password3");
        user3.setEmail("email3");
        userRepository.save(user3);
        var friend = new Friend();
        friend.setToUser(user);
        friend.setFromUser(user2);
        friend.setStatus("friend");
        friendRepository.save(friend);
        var friend2 = new Friend();
        friend2.setToUser(user);
        friend2.setFromUser(user3);
        friend2.setStatus("friend");
        friendRepository.save(friend2);
        var post2 = new Post();
        post2.setTitle("title2");
        post2.setDescription("description2");
        post2.setUserId(user2);
        postRepository.save(post2);
        var post3 = new Post();
        post3.setTitle("title3");
        post3.setDescription("description3");
        post3.setUserId(user3);
        postRepository.save(post3);
        var found = postRepository.findByFriendsPosts(user, Pageable.ofSize(1));
        assertThat(found.getTotalPages()).isEqualTo(2);
    }*/
}