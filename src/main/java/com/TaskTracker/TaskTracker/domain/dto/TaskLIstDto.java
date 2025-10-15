package com.TaskTracker.TaskTracker.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskLIstDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) {
}
