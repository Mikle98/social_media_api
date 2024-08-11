package ru.job4j.social.media.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.social.media.api.model.User;
import ru.job4j.social.media.api.model.UsersDTO;
import ru.job4j.social.media.api.repository.PostRepository;
import ru.job4j.social.media.api.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserRepository userRepository;

    @GetMapping("/getUsers")
    public List<UsersDTO> getUsers(@RequestParam(value = "userId") List<Long> userId) {
        List<UsersDTO> users = userRepository.findAllUsersAndReturnDTO(userId);
        return users;
    }
}
