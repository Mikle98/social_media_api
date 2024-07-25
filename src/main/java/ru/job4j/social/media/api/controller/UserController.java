package ru.job4j.social.media.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.social.media.api.model.User;
import ru.job4j.social.media.api.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsers(@PathVariable long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        var optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        if (userRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return  userRepository.findById(id).isEmpty()
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
    }
}
