package com.example.task_tracker.api.controllers;


import com.example.task_tracker.api.dto.ProjectDto;
import com.example.task_tracker.api.exeptions.BadRequestException;
import com.example.task_tracker.api.factories.ProjectDtoFactory;
import com.example.task_tracker.store.entities.ProjectEntity;
import com.example.task_tracker.store.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {

  ProjectDtoFactory projectDtoFactory;
  ProjectRepository projectRepository;
  public static final String CREATE_PROJECT = "/api/projects";

 @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name){

     projectRepository.findByName(name)
             .ifPresent(project -> {
                 throw new BadRequestException(String.format("Project %s already exists.", name));
             });
     ProjectEntity project = projectRepository.saveAndFlush(
             ProjectEntity.builder()
                     .name(name)
                     .build()
     );

     return projectDtoFactory.makeProjectDto(project);
 }


}
