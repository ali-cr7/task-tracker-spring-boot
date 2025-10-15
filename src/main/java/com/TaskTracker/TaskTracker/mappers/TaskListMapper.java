package com.TaskTracker.TaskTracker.mappers;

import com.TaskTracker.TaskTracker.domain.dto.TaskDto;
import com.TaskTracker.TaskTracker.domain.dto.TaskLIstDto;
import com.TaskTracker.TaskTracker.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskLIstDto taskLIstDto);

    TaskLIstDto toDto(TaskList taskList);
}
