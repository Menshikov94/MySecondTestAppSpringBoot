package ru.menshikov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {
    EMPTY(""),
    VALIDATION("Ошибка валидации"),
    UNSAPPORTED("Произошла напредвиденная ошибка"),
    UNKNOWN("Не поддерживаемая ошибка");

    private final String description;
    ErrorMessages(String description) {

        this.description = description;
    }
    @JsonValue
    public String getName() {

        return description;
    }
}
