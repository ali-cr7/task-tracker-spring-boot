package com.TaskTracker.TaskTracker.services;

import com.TaskTracker.TaskTracker.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskServices {
 public List<Task> ListOfTasks (UUID taskListId) ;
 public Task createTask (UUID taskListId , Task task);
 public Task getTask(UUID taskListId, UUID taskId);
 public Task updateTask(UUID taskListId, UUID taskId, Task task);
 public void deleteTask(UUID taskListId, UUID taskId);

}
