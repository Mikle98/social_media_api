package ru.job4j.social.media.api.controller;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.social.media.api.model.Post;
import ru.job4j.social.media.api.repository.PostRepository;
import ru.job4j.social.media.api.validation.Operation;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostRequest {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Post> postPost(@Valid @RequestBody Post post) {
        postRepository.save(post);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(post);
    }

    @PutMapping
    public ResponseEntity<Void> updatePost(@RequestBody Post post) {
        var optionalPost = postRepository.findById(post.getId());
        if (optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        postRepository.save(post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        postRepository.deleteById(id);
        return postRepository.findById(id).isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
