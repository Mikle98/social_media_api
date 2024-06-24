package ru.job4j.social.media.api.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.media.api.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
}
