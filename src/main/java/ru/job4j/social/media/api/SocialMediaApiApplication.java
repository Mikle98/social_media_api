package ru.job4j.social.media.api;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.job4j.social.media.api.model.User;
import ru.job4j.social.media.api.repository.UserRepository;


@SpringBootApplication
public class SocialMediaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApiApplication.class, args);
	}
}
