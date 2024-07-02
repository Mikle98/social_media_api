package ru.job4j.social.media.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.media.api.model.Friend;
import ru.job4j.social.media.api.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("FROM User WHERE login = ?1 and password = ?2")
    public Optional<User> findByUserWithHQL(String login, String password);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            UPDATE User SET login = ?2
            WHERE login = ?1
            """)
    public int changeNameWithHQL(String oldName, String newName);

    @Query("""
            SELECT
            new User (
            u.id,
            u.login,
            u.password,
            u.email)
            FROM Friend f 
            INNER JOIN User u
                on u.id = f.fromUser.id
            WHERE f.toUser.id = ?1 and f.status = 'follower'
            """)
    public List<User> findAllFollowers(long id);

    @Query("""
            SELECT
            new User (
            u.id,
            u.login,
            u.password,
            u.email)
            FROM Friend f 
            INNER JOIN User u
                on u.id = f.fromUser.id
            WHERE f.toUser.id = ?1 and f.status = 'friend'
            """)
    public List<User> findAllFriend(long id);
}
