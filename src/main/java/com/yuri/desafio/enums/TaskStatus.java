package com.yuri.desafio.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum TaskStatus {
    PENDING("pending"),
    FINISHED("finished");

    private String status;

    @JsonCreator
    public static TaskStatus decode(final String status) {
        return Stream.of(TaskStatus.values()).filter(targetEnum -> targetEnum.status.equals(status)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return status;
    }
}
