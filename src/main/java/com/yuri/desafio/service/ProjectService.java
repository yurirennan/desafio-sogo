package com.yuri.desafio.service;

import com.yuri.desafio.dto.project.CreateProjectDTO;
import com.yuri.desafio.model.Project;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ProjectService {
    void saveProject(CreateProjectDTO createProjectDTO);
    void addTaskToProject(Long taskId, Long projectId);
    Project listProjectTasks(Long projectId);
}
