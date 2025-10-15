package com.TaskTracker.TaskTracker.services;

import com.TaskTracker.TaskTracker.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
     List<TaskList> listTask();
     TaskList createTaskList(TaskList taskList);
     Optional<TaskList> getTaskList(UUID id);
     TaskList updateTaskList(UUID taskListId , TaskList taskList);
     void  deleteTaskId(UUID taskListId);

}
