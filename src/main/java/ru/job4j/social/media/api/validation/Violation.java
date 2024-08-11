package ru.job4j.social.media.api.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Violation {
    private final String fieldName;
    private final String message;
}
