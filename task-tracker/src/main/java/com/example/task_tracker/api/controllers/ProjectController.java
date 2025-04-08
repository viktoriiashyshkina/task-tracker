package com.example.task_tracker.api.controllers;

import com.example.task_tracker.api.dto.AskDto;
import com.example.task_tracker.api.dto.ProjectDto;
import com.example.task_tracker.api.exeptions.BadRequestException;
import com.example.task_tracker.api.exeptions.NotFoundException;
import com.example.task_tracker.api.factories.ProjectDtoFactory;
import com.example.task_tracker.store.entities.ProjectEntity;
import com.example.task_tracker.store.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {

    ProjectDtoFactory projectDtoFactory;
    ProjectRepository projectRepository;

    public static final String FETCH_PROJECT = "/api/projects";
    public static final String CREATE_PROJECT = "/api/projects";
    public static final String EDIT_PROJECT = "/api/projects/{project_id}";
    public static final String DELETE_PROJECT = "/api/projects/{project_id}";
    public static final String CREATE_OR_UPDATE_PROJECT = "/api/projects";

    @GetMapping(FETCH_PROJECT)
    public List<ProjectDto> fetchProjects(
            @RequestParam(value = "prefix_name", required = false) Optional<String> optionalPrefixName) {

        optionalPrefixName = optionalPrefixName.filter(prefixName -> !prefixName.trim().isEmpty());
        Stream<ProjectEntity> projectEntityStream = optionalPrefixName
                .map(projectRepository::streamAllByNameStartsWithIgnoreCase)
                .orElseGet(projectRepository::streamAll);

        return projectEntityStream.map(projectDtoFactory::makeProjectDto)
                .collect(Collectors.toList());

    }
//
//    @PostMapping(CREATE_PROJECT)
//    public ProjectDto createProject(@RequestParam String projectName) {
//        if (projectName.trim().isEmpty()) {
//            throw new BadRequestException("Name can't be empty");
//        }
//
//        projectRepository.findByName(projectName)
//                .ifPresent(project -> {
//                    throw new BadRequestException(String.format("Project %s already exists.", projectName));
//                });
//        ProjectEntity project = projectRepository.saveAndFlush(
//                ProjectEntity.builder()
//                        .name(projectName
//                        )
//                        .build()
//        );
//
//        return projectDtoFactory.makeProjectDto(project);
//    }

    @PutMapping(CREATE_OR_UPDATE_PROJECT)
    public ProjectDto createOrUpdateProject(
            @RequestParam(value = "project_id", required = false) Optional<Long> optionalProjectId,
            @RequestParam(value = "project_name", required = false) Optional<String> optionalProjectName) {

        optionalProjectName = optionalProjectName.filter(projectName -> !projectName.trim().isEmpty());
        boolean isCreate = !optionalProjectId.isPresent();

        ProjectEntity project = optionalProjectId
                .map(this::getProjectOrThrowException)
                .orElseGet(() -> ProjectEntity.builder().build());
        if (isCreate && !optionalProjectName.isPresent()) {
            throw new BadRequestException("Project name can't be empty");
        }

        optionalProjectName
                .ifPresent(projectName -> {

                    projectRepository.findByName(projectName)
                            .filter(anotherProject -> !Objects.equals(anotherProject.getId(), project.getId()))
                            .ifPresent(anotherProject -> {
                                throw new BadRequestException(String.format("Project %s already exists.", projectName)
                                );
                            });
                    project.setName(projectName);
                });
        final ProjectEntity savedProject = projectRepository.saveAndFlush(project);
        return projectDtoFactory.makeProjectDto(project);
    }

//
//    @PatchMapping(EDIT_PROJECT)
//    public ProjectDto editProject(
//            @PathVariable("project_id") Long projectId,
//            @RequestParam String projectName){
//
//        ProjectEntity project = getProjectOrThrowException(projectId);
//
//        projectRepository.findByName(projectName)
//                .filter(anotherProject -> !Objects.equals(anotherProject.getId(), projectId))
//                .ifPresent(anotherProject -> {
//                    throw new BadRequestException(String.format("Project %s already exists.", projectName));
//                });
//        project.setName(projectName);
//        project = projectRepository.saveAndFlush(project);
//
//        return projectDtoFactory.makeProjectDto(project);
//    }

    @DeleteMapping(DELETE_PROJECT)
    public AskDto deleteProject(@PathVariable("project_id") Long projectId) {

        getProjectOrThrowException(projectId);
        projectRepository.deleteById(projectId);
        return AskDto.makeDefault(true);
    }

    private ProjectEntity getProjectOrThrowException(Long projectId) {
      return projectRepository
                .findById(projectId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Project with ID %d doesn't exist", projectId)));
    }
}
