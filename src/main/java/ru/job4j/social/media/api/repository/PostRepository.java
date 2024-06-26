package ru.job4j.social.media.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.media.api.model.Post;
import ru.job4j.social.media.api.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> findByUser(User user);

    public List<Post> findByCreatedGreaterThanEqualAndCreatedLessThanEqual(LocalDateTime createdStart, LocalDateTime createdEnd);

    public Page<Post> findByOrderByCreatedDesc(Pageable pageable);
}
