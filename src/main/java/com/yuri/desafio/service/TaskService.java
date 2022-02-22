package com.yuri.desafio.service;

import com.yuri.desafio.dto.task.CreateTaskDTO;
import com.yuri.desafio.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void saveTask(Long userId, CreateTaskDTO taskDTO);
    void finishTask(Long taskId);
    List<Task> findAll(Long userId);
    List<Task> findAllPendingByDay(Long userId, String date);
}
