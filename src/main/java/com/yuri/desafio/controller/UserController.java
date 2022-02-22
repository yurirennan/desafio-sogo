package com.yuri.desafio.controller;

import com.yuri.desafio.dto.task.ResponseTaskDTO;
import com.yuri.desafio.dto.user.CreateUserDTO;
import com.yuri.desafio.dto.user.ResponseUserDTO;
import com.yuri.desafio.model.Task;
import com.yuri.desafio.service.impl.TaskServiceImpl;
import com.yuri.desafio.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userService;
    private TaskServiceImpl taskService;

    public UserController(UserServiceImpl userService, TaskServiceImpl taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @ApiOperation(value = "This method is used to save a user.")
    @PostMapping
    private ResponseEntity saveUser(@RequestBody @Valid CreateUserDTO userDTO) {
        this.userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "This method is used to list a user's data.")
    @GetMapping
    private ResponseEntity listUserData() {
        Object userIdLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ResponseUserDTO user = this.userService.listUserData(Long.valueOf(userIdLogged.toString()));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @ApiOperation(value = "This method is used to list all user's tasks.")
    @GetMapping("/tasks")
    private ResponseEntity listAllTasks() {
        Object userIdLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> tasks = taskService.findAll(Long.valueOf(userIdLogged.toString()));
        return ResponseEntity.status(HttpStatus.OK)
                .body(tasks.stream().map(ResponseTaskDTO::convertToDto).collect(Collectors.toList()));
    }

    @ApiOperation(value = "This method is used to list all pending tasks up to the given date.")
    @GetMapping("/tasks/{date}")
    private ResponseEntity listAllTasksByDay(@PathVariable String date) {
        Object userIdLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = Long.valueOf(userIdLogged.toString());
        List<Task> tasks = taskService.findAllPendingByDay(userId, date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(tasks.stream().map(ResponseTaskDTO::convertToDto).collect(Collectors.toList()));
    }
}
