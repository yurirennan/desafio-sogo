package com.yuri.desafio.service.impl;

import com.yuri.desafio.dto.project.CreateProjectDTO;
import com.yuri.desafio.exceptions.BadRequestException;
import com.yuri.desafio.exceptions.ProjectNotFoundException;
import com.yuri.desafio.exceptions.TaskNotFoundException;
import com.yuri.desafio.model.Project;
import com.yuri.desafio.model.Task;
import com.yuri.desafio.model.User;
import com.yuri.desafio.repository.ProjectRepository;
import com.yuri.desafio.repository.TaskRepository;
import com.yuri.desafio.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void saveProject(CreateProjectDTO createProjectDTO) {
        Optional<Project> projectOptional = this.projectRepository.findByTitle(createProjectDTO.getTitle());

        if(projectOptional.isPresent()) {
            throw new BadRequestException("Project already exists!");
        }

        Project project = new Project();

        project.setTitle(createProjectDTO.getTitle());
        this.projectRepository.save(project);
    }

    @Override
    public void addTaskToProject(Long taskId, Long projectId) {
        Optional<Project> projectOptional = this.projectRepository.findById(projectId);

        if(!projectOptional.isPresent()) {
            throw new ProjectNotFoundException("Project not found!");
        }

        Optional<Task> taskOptional = this.taskRepository.findById(taskId);

        if(!taskOptional.isPresent()) {
            throw new TaskNotFoundException("Task not found!");
        }

        Project project = projectOptional.get();
        Task task = taskOptional.get();

        project.getTasks().add(task);
        task.setProject(project);

        this.projectRepository.save(project);
        this.taskRepository.save(task);
    }

    @Override
    public Project listProjectTasks(Long projectId) {
        Optional<Project> projectOptional = this.projectRepository.findById(projectId);

        if(!projectOptional.isPresent()) {
            throw new ProjectNotFoundException("Project not found!");
        }

        Project project = projectOptional.get();

        return project;
    }
}
