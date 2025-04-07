package com.example.task_tracker.api.factories;

import com.example.task_tracker.api.dto.ProjectDto;
import com.example.task_tracker.store.entities.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoFactory {

    public ProjectDto makeProjectDto(ProjectEntity entity) {
        return ProjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .updatedAt(entity.getUpdatedAt())
                .createAt(entity.getCreateAt())
                .build();
    }
}
