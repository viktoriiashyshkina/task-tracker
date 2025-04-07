package com.example.task_tracker.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class TaskDto {
        @NonNull
        Long id;

        @NonNull
        String name;

        @CreationTimestamp
        @JsonProperty("create_at")
        Instant createAt;

        @NonNull
        String description;
}
