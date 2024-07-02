package ru.job4j.social.media.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.social.media.api.model.Post;
import ru.job4j.social.media.api.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> findByUserId(User user);

    public List<Post> findByCreatedGreaterThanEqualAndCreatedLessThanEqual(LocalDateTime createdStart, LocalDateTime createdEnd);

    public Page<Post> findByOrderByCreatedDesc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("""
        UPDATE Post
        SET title = :title, description = :description
        WHERE id = :id
    """)
    public Integer updateTitleAndDescription(@Param("id") Long id,
                                             @Param("title") String title,
                                             @Param("description") String description);

    @Transactional
    @Modifying
    @Query("""
        UPDATE Post 
        SET fileId = null 
        WHERE id = :id
    """
    )
    public Integer deleteFile(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("""
        DELETE FROM Post
        WHERE id = :id
    """
    )
    public Integer deletePost(@Param("id") Long id);

    /*@Query("""
        SELECT
        p.id,
        p.title,
        p.description,
        p.created,
        p.userId,
        p.fileId
        FROM Post p
        INNER JOIN Friend f
            ON f.fromUser = p.userId
            AND f.toUser = :toUser
        ORDER BY p.created DESC
    """
    )
    public Page<Post> findByFriendsPosts(@Param("toUser") User toUser, Pageable pageable);*/
}
