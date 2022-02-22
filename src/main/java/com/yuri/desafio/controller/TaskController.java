package com.yuri.desafio.controller;

import com.yuri.desafio.dto.task.CreateTaskDTO;
import com.yuri.desafio.service.impl.TaskServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "This method is used to save a task.")
    @PostMapping("/save")
    private ResponseEntity saveTask(@RequestBody @Valid CreateTaskDTO taskDTO) {
        Object userIdLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = Long.valueOf(userIdLogged.toString());
        taskService.saveTask(userId, taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "This method is used to finish a task.")
    @PostMapping("/finish/{taskId}")
    private ResponseEntity finishTask(@PathVariable Long taskId) {
        taskService.finishTask(taskId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
