package com.TaskTracker.TaskTracker.mappers;
import com.TaskTracker.TaskTracker.domain.dto.TaskDto;
import com.TaskTracker.TaskTracker.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);

}
