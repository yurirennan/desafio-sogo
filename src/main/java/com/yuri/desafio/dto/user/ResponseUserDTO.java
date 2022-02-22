package com.yuri.desafio.dto.user;

import com.yuri.desafio.model.Task;
import com.yuri.desafio.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseUserDTO {
    private Long id;
    private String username;
    private List<Task> tasks;
    private Long timeLimitDay;

    public static ResponseUserDTO convertToDto(User user) {
        ResponseUserDTO userDTO = new ResponseUserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setTimeLimitDay(user.getTimeLimitDay());
        userDTO.setTasks(user.getTasks());

        return userDTO;
    }
}
