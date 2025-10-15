package com.TaskTracker.TaskTracker.controllers;

import com.TaskTracker.TaskTracker.domain.dto.TaskDto;
import com.TaskTracker.TaskTracker.domain.entities.Task;
import com.TaskTracker.TaskTracker.mappers.TaskMapper;
import com.TaskTracker.TaskTracker.services.TaskServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskMapper taskMapper;
    private final TaskServices taskServices;

    public TasksController(TaskMapper taskMapper, TaskServices taskServices) {
        this.taskMapper = taskMapper;
        this.taskServices = taskServices;
    }
    @GetMapping
    public List<TaskDto> listsTasks(@PathVariable("task_list_id") UUID taskListId) {
        return  taskServices.ListOfTasks(taskListId).
                stream().map(taskMapper::toDto).
                toList();

    }
    @GetMapping(path = "/{task_id}")
    public TaskDto getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ) {
        return taskMapper.toDto(taskServices.getTask(taskListId, taskId));
    }
    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId , @RequestBody TaskDto taskDto){
        Task createdTask = taskServices.createTask(
                taskListId,
                taskMapper.fromDto(taskDto)

        );
        return taskMapper.toDto(createdTask);

    }
    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId,
            @RequestBody TaskDto taskDto
    ) {
        Task updated = taskServices.updateTask(taskListId, taskId, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updated);
    }

    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId
    ) {
        taskServices.deleteTask(taskListId, taskId);
    }
}
