package com.yuri.desafio.handlers;

import com.yuri.desafio.exceptions.BadRequestException;
import com.yuri.desafio.exceptions.ProjectNotFoundException;
import com.yuri.desafio.exceptions.TaskNotFoundException;
import com.yuri.desafio.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBadRequestException(BadRequestException bre) {
        return new ApiErrors(bre.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleUserNotFoundException(UserNotFoundException bre) {
        return new ApiErrors(bre.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleTaskNotFoundException(TaskNotFoundException bre) {
        return new ApiErrors(bre.getMessage());
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleProjectNotFoundException(ProjectNotFoundException bre) {
        return new ApiErrors(bre.getMessage());
    }
}
