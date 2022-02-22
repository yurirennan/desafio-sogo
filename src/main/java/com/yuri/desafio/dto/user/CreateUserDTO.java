package com.yuri.desafio.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserDTO {
    @NotBlank(message = "O campo username não pode ser vazio!")
    private String username;
    @NotBlank(message = "O campo password não pode ser vazio!")
    private String password;

    @NotNull(message = "O campo timeLimitDay não pode ser vazio!")
    private Long timeLimitDay;
}
