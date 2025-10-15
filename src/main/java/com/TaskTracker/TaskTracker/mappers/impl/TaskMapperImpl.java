package com.TaskTracker.TaskTracker.mappers.impl;

import com.TaskTracker.TaskTracker.domain.dto.TaskDto;
import com.TaskTracker.TaskTracker.domain.entities.Task;
import com.TaskTracker.TaskTracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new  TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDuDate(),
                task.getPriority(),
                task.getStatus()



        );
    }
}
