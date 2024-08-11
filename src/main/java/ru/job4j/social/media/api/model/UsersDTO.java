package ru.job4j.social.media.api.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsersDTO {
    @EqualsAndHashCode.Include
    private Long userId;
    private String username;
    private Post posts;
}
