package com.yuri.desafio.dto.task;

import com.yuri.desafio.enums.TaskPriority;
import com.yuri.desafio.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CreateTaskDTO {
    
    @NotBlank(message = "O campo title não pode ser vazio!")
    private String title;

    @NotNull(message = "O campo priority não pode ser vazio!")
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @NotNull(message = "O campo minutesTimeFrequency não pode ser vazio!")
    private Long minutesTimeFrequency;

    @NotBlank(message = "O campo title não pode ser vazio!")
    private String maxFinishDay;
}
