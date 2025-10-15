package com.TaskTracker.TaskTracker.services.impl;

import com.TaskTracker.TaskTracker.domain.entities.Task;
import com.TaskTracker.TaskTracker.domain.entities.TaskList;
import com.TaskTracker.TaskTracker.domain.entities.TaskPriority;
import com.TaskTracker.TaskTracker.domain.entities.TaskStatus;
import com.TaskTracker.TaskTracker.repositories.TaskListRepositories;
import com.TaskTracker.TaskTracker.repositories.TaskRepositories;
import com.TaskTracker.TaskTracker.services.TaskServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class TaskServicesImpl  implements TaskServices {

    private final TaskRepositories taskRepositories;
    private final TaskListRepositories taskListRepositories;

    public TaskServicesImpl(TaskRepositories taskRepositories, TaskListRepositories taskListRepositories) {
        this.taskRepositories = taskRepositories;
        this.taskListRepositories = taskListRepositories;
    }

    @Override
    public List<Task> ListOfTasks(UUID taskListId) {
        return  taskRepositories.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
//        if(null!= task.getId()) {
//            throw new IllegalArgumentException("task already has an ID!");
//        }
//        if(null!= task.getTitle() || task.getTitle().isBlank()) {
//            throw new IllegalArgumentException("Task must have a title !") ;
//        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
       TaskList taskList =  taskListRepositories.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID Provided!"));

        LocalDateTime now = LocalDateTime.now();
       Task taskToSave = new Task(
               null,
               task.getTitle(),
               task.getDescription(),
               task.getDuDate(),
               taskStatus,taskPriority,
               taskList,
               now,
               now


       );

        return  taskRepositories.save(taskToSave);
    }

    @Override
    public Task getTask(UUID taskListId, UUID taskId) {
        return taskRepositories.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found for provided list and id"));
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        Task existing = taskRepositories.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found for provided list and id"));

        if (task.getTitle() != null && !task.getTitle().isBlank()) {
            existing.setTitle(task.getTitle());
        }
        // description is nullable; allow explicit null to clear
        if (task.getDescription() != null || task.getDescription() == null) {
            existing.setDescription(task.getDescription());
        }
        if (task.getDuDate() != null || task.getDuDate() == null) {
            existing.setDuDate(task.getDuDate());
        }
        if (task.getPriority() != null) {
            existing.setPriority(task.getPriority());
        }
        if (task.getStatus() != null) {
            existing.setStatus(task.getStatus());
        }
        existing.setUpdated(LocalDateTime.now());

        return taskRepositories.save(existing);
    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        Task existing = taskRepositories.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found for provided list and id"));
        taskRepositories.delete(existing);
    }
}
