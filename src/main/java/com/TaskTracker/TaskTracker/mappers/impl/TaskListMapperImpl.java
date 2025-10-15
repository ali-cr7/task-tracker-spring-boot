package com.TaskTracker.TaskTracker.mappers.impl;

import com.TaskTracker.TaskTracker.domain.dto.TaskLIstDto;
import com.TaskTracker.TaskTracker.domain.entities.Task;
import com.TaskTracker.TaskTracker.domain.entities.TaskList;
import com.TaskTracker.TaskTracker.domain.entities.TaskStatus;
import com.TaskTracker.TaskTracker.mappers.TaskListMapper;
import com.TaskTracker.TaskTracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class TaskListMapperImpl implements TaskListMapper {
    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskLIstDto taskLIstDto) {
        return new TaskList(
                taskLIstDto.id(),
                taskLIstDto.title(),
                taskLIstDto.description(),
                Optional.ofNullable(taskLIstDto.tasks()).map(tasks -> tasks.stream()
                        .map(taskMapper::fromDto).toList()

                ).orElse(null),
                null,
                null

        );
    }

    @Override
    public TaskLIstDto toDto(TaskList taskList) {
        return new TaskLIstDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size).
                        orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList()
                        ).orElse(null)
        );

    }   private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks) {
            return null;
        }
       long closedTaskedCount= tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()
        ).count();
        return (double)closedTaskedCount / tasks.size();

    }
}
