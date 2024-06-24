package ru.job4j.social.media.api.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.social.media.api.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
