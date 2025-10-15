package com.TaskTracker.TaskTracker.domain.dto;

import com.TaskTracker.TaskTracker.domain.entities.TaskPriority;
import com.TaskTracker.TaskTracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status

) {

}
