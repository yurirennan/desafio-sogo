package com.yuri.desafio.dto.task;

import com.yuri.desafio.dto.user.ResponseUserDTO;
import com.yuri.desafio.enums.TaskPriority;
import com.yuri.desafio.enums.TaskStatus;
import com.yuri.desafio.model.Task;
import com.yuri.desafio.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
public class ResponseTaskDTO {
    private Long id;
    private String title;
    private TaskStatus status;
    private TaskPriority priority;
    private Long minutesTimeFrequency;
    private LocalDate maxFinishDay;

    public static ResponseTaskDTO convertToDto(Task task) {
        ResponseTaskDTO taskDTO = new ResponseTaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setMinutesTimeFrequency(task.getMinutesTimeFrequency());
        taskDTO.setMaxFinishDay(task.getMaxFinishDay());

        return taskDTO;
    }
}
