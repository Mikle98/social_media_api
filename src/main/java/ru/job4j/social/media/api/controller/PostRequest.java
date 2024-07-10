package ru.job4j.social.media.api.controller;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.social.media.api.model.Post;
import ru.job4j.social.media.api.repository.PostRepository;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostRequest {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ResponseEntity<Iterable<Post>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Post> postPost(@RequestBody Post post) {
        return ResponseEntity.ok(postRepository.save(post));
    }

    @PutMapping
    public void updatePost(@RequestBody Post post) {
        postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}
