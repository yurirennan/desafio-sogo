package com.yuri.desafio.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum TaskPriority {
    NORMAL("normal"), MEDIUM("medium"), HIGH("high");

    private String priority;

    @JsonCreator
    public static TaskPriority decode(final String priority) {
        return Stream.of(TaskPriority.values()).filter(targetEnum -> targetEnum.priority.equals(priority)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return priority;
    }
}
