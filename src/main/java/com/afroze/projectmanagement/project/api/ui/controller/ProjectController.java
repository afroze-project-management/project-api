package com.afroze.projectmanagement.project.api.ui.controller;
import com.afroze.projectmanagement.project.api.dto.ProjectDto;
import com.afroze.projectmanagement.project.api.dto.ProjectSummaryDto;
import com.afroze.projectmanagement.project.api.dto.TaskDto;
import com.afroze.projectmanagement.project.api.exception.ProjectAlreadyExistsException;
import com.afroze.projectmanagement.project.api.exception.ProjectNotFoundException;
import com.afroze.projectmanagement.project.api.security.Permissions;
import com.afroze.projectmanagement.project.api.service.ProjectService;
import com.afroze.projectmanagement.project.api.ui.model.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProjectController {

    private final ProjectService projectService;
    private final ModelMapper mapper;

    private final Logger logger;

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("EI_EXPOSE_REP2")
    public ProjectController(ProjectService projectService, ModelMapper mapper) {
        this.projectService = projectService;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.mapper = mapper;

        this.logger = LoggerFactory.getLogger(ProjectController.class);
    }

    @GetMapping("/project")
    @PreAuthorize("hasAuthority('" + Permissions.READ_PROJECT + "')")
    public ResponseEntity<HttpResponseModel<List<ProjectSummaryResponseModel>>> getAll() {
        List<ProjectSummaryDto> projects = projectService.getAll();
        if(projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ProjectSummaryResponseModel> response = mapper.map(projects, new TypeToken<List<ProjectSummaryResponseModel>>(){}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.success(response));
    }

    @GetMapping("/company/{companyId}/projects")
    @PreAuthorize("hasAuthority('" + Permissions.READ_PROJECT + "')")
    public ResponseEntity<HttpResponseModel<List<ProjectSummaryResponseModel>>> getAllByCompany(
            @PathVariable("companyId") int companyId) {

        logger.info("Get All Projects by Company called");

        List<ProjectSummaryDto> projects = projectService.getAllByCompanyId(companyId);
        if(projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ProjectSummaryResponseModel> response = mapper.map(projects, new TypeToken<List<ProjectSummaryResponseModel>>(){}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.success(response));
    }

    @GetMapping("/project/{projectId}/")
    @PreAuthorize("hasAuthority('" + Permissions.READ_PROJECT + "')")
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> getById(@PathVariable("projectId") int projectId) {
        try {
            ProjectDto project = projectService.getById(projectId);
            ProjectResponseModel response = mapper.map(project, ProjectResponseModel.class);
            return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.success(response));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponseModel.failure(null, e.getLocalizedMessage()));
        }
    }

    @PostMapping("/project")
    @PreAuthorize("hasAuthority('" + Permissions.WRITE_PROJECT + "')")
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> create(@RequestBody @Valid ProjectRequestModel project) {
        ProjectDto dto = mapper.map(project, ProjectDto.class);

        try {
            ProjectDto createdProject = projectService.create(dto);
            ProjectResponseModel response = mapper.map(createdProject, ProjectResponseModel.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(HttpResponseModel.success(response));
        } catch (ProjectAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpResponseModel.failure(null, e.getLocalizedMessage()));
        }
    }

    @PostMapping("/project/{projectId}/tasks")
    @PreAuthorize("hasAuthority('" + Permissions.WRITE_TASK + "')")
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> addTask(
            @PathVariable("projectId") int projectId,
            @RequestBody @Valid TaskRequestModel taskModel) {
        try {
            TaskDto taskDtos = mapper.map(taskModel, TaskDto.class);
            ProjectDto updatedProject = projectService.addTaskToProject(projectId, taskDtos);
            ProjectResponseModel response = mapper.map(updatedProject, ProjectResponseModel.class);

            return ResponseEntity.status(HttpStatus.CREATED).body(HttpResponseModel.success(response));
        } catch (ProjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponseModel.failure(null, e.getLocalizedMessage()));
        }
    }

    @PostMapping("/project/{projectId}/bulktasks")
    @PreAuthorize("hasAuthority('" + Permissions.WRITE_TASK + "')")
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> addTasks(
            @PathVariable("projectId") int projectId,
            @RequestBody @Valid TaskListRequestModel taskModel) {
        try {
            List<TaskDto> taskDtos = mapper.map(taskModel.getTasks(), new TypeToken<List<TaskDto>>(){}.getType());
            ProjectDto updatedProject = projectService.addTasksToProject(projectId, taskDtos);
            ProjectResponseModel response = mapper.map(updatedProject, ProjectResponseModel.class);

            return ResponseEntity.status(HttpStatus.CREATED).body(HttpResponseModel.success(response));
        } catch (ProjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponseModel.failure(null, e.getLocalizedMessage()));
        }
    }

    @DeleteMapping("/project/{projectId}/")
    @PreAuthorize("hasAuthority('" + Permissions.DELETE_PROJECT + "')")
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> delete(
            @PathVariable("projectId") int projectId) {
        projectService.delete(projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @DeleteMapping("/company/{companyId}/projects")
    @PreAuthorize("hasAuthority('" + Permissions.DELETE_PROJECT + "')")
    public ResponseEntity<HttpResponseModel<List<ProjectSummaryResponseModel>>> deleteAllByCompany(
            @PathVariable("companyId") int companyId) {
        projectService.deleteAllByCompanyId(companyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/project/{projectId}/")
    @PreAuthorize("hasAuthority('" + Permissions.UPDATE_PROJECT + "')")
    public ResponseEntity<HttpResponseModel<ProjectResponseModel>> update(
            @PathVariable("projectId") long projectId,
            @RequestBody @Valid ProjectResponseModel project) {
        ProjectDto dto = mapper.map(project, ProjectDto.class);
        try {
            ProjectDto updatedCompany = projectService.update(projectId, dto);
            ProjectResponseModel response = mapper.map(updatedCompany, ProjectResponseModel.class);
            return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.success(response));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpResponseModel.failure(null, e.getLocalizedMessage()));
        }
    }
}
