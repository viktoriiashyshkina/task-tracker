package com.example.task_tracker.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskStateDto {
    @NonNull
    Long id;

    @NonNull
    String name;

    @JsonProperty("left_task_state_id")
    Long leftTaskStateId;

    @JsonProperty("right_task_state_id")
    Long rightTaskStateId;

    @NonNull
    @JsonProperty("created_at")
    Instant createdAt;

    @NonNull
    List<TaskDto> tasks;


    public @NonNull List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(@NonNull List<TaskDto> tasks) {
        this.tasks = tasks;
    }

    public @NonNull Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getRightTaskStateId() {
        return rightTaskStateId;
    }

    public void setRightTaskStateId(Long rightTaskStateId) {
        this.rightTaskStateId = rightTaskStateId;
    }

    public Long getLeftTaskStateId() {
        return leftTaskStateId;
    }

    public void setLeftTaskStateId(Long leftTaskStateId) {
        this.leftTaskStateId = leftTaskStateId;
    }

    public @NonNull String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public @NonNull Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }
}
