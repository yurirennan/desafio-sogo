package com.yuri.desafio.service.impl;

import com.yuri.desafio.dto.task.CreateTaskDTO;
import com.yuri.desafio.enums.TaskStatus;
import com.yuri.desafio.exceptions.BadRequestException;
import com.yuri.desafio.exceptions.TaskNotFoundException;
import com.yuri.desafio.exceptions.UserNotFoundException;
import com.yuri.desafio.model.Task;
import com.yuri.desafio.model.User;
import com.yuri.desafio.repository.TaskRepository;
import com.yuri.desafio.repository.UserRepository;
import com.yuri.desafio.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveTask(Long userId, CreateTaskDTO taskDTO) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found!");
        }

        Optional<Task> taskOptional = this.taskRepository.findByTitle(taskDTO.getTitle());

        if(taskOptional.isPresent()) {
            throw new BadRequestException("Task already exists!");
        }

        User user = userOptional.get();
        Task task = new Task();

        task.setTitle(taskDTO.getTitle());
        task.setPriority(taskDTO.getPriority());
        task.setMinutesTimeFrequency(taskDTO.getMinutesTimeFrequency());
        task.setStatus(TaskStatus.PENDING);
        task.setMaxFinishDay(LocalDate.parse(taskDTO.getMaxFinishDay()));
        task.setUser(user);

        user.getTasks().add(task);

        this.taskRepository.save(task);
        this.userRepository.save(user);
    }

    @Override
    public void finishTask(Long taskId) {
        Optional<Task> taskOptional = this.taskRepository.findById(taskId);

        if(!taskOptional.isPresent()) {
            throw new TaskNotFoundException("Task not found!");
        }

        Task task = taskOptional.get();

        task.setStatus(TaskStatus.FINISHED);
        this.taskRepository.save(task);
    }

    @Override
    public List<Task> findAll(Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found!");
        }

        User user = userOptional.get();

        List<Task> tasks = this.taskRepository.findAllbyUserIdOrderByPriority(user.getId());

        return tasks;
    }

    @Override
    public List<Task> findAllPendingByDay(Long userId, String date) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found!");
        }

        List<Task> tasksCreated = this.taskRepository.findAllPeding(userId);
        List<Task> resultTasks = new ArrayList<>();

        for(int i = 0; i < tasksCreated.size(); i++) {
            if (tasksCreated.get(i).getMaxFinishDay().isBefore(LocalDate.parse(date))) {
                resultTasks.add(tasksCreated.get(i));
            }
        }

        return resultTasks;
    }
}
