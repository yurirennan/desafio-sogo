package com.yuri.desafio.dto.project;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateProjectDTO {
    @NotBlank(message = "O campo title não pode ser vazio!")
    private String title;
}
