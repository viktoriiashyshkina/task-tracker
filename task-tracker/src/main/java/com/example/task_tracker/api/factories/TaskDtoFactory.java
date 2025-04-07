package com.example.task_tracker.api.factories;

import com.example.task_tracker.api.dto.ProjectDto;
import com.example.task_tracker.api.dto.TaskDto;
import com.example.task_tracker.store.entities.ProjectEntity;
import com.example.task_tracker.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {


        public TaskDto makeTaskDto(TaskEntity entity){
            return TaskDto.builder().
                    id(entity.getId())
                    .name(entity.getName())
                    .createAt(entity.getCreateAt())
                    .description(entity.getDescription())
                    .build();
        }
}
