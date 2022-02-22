package com.yuri.desafio.controller;

import com.yuri.desafio.dto.project.CreateProjectDTO;
import com.yuri.desafio.model.Project;
import com.yuri.desafio.service.impl.ProjectServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @ApiOperation(value = "This method is used to save a project.")
    @PostMapping
    private ResponseEntity saveProject(@RequestBody @Valid CreateProjectDTO createProjectDTO) {
        projectService.saveProject(createProjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "This method is used to list the task data.")
    @GetMapping("/{projectId}")
    private ResponseEntity listProjectTasks(@PathVariable Long projectId) {
        Project project = projectService.listProjectTasks(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @ApiOperation(value = "This method is used to link a task to a project.")
    @PostMapping("{projectId}/add-task/{taskId}")
    private ResponseEntity addTaskToProject(@PathVariable Long taskId,
                                            @PathVariable Long projectId) {
        projectService.addTaskToProject(taskId, projectId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
