package com.TaskTracker.TaskTracker.controllers;

import com.TaskTracker.TaskTracker.domain.dto.TaskLIstDto;
import com.TaskTracker.TaskTracker.domain.entities.TaskList;
import com.TaskTracker.TaskTracker.mappers.TaskListMapper;
import com.TaskTracker.TaskTracker.repositories.TaskListRepositories;
import com.TaskTracker.TaskTracker.services.TaskListService;
import org.hibernate.query.criteria.JpaConflictUpdateAction;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private  final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskLIstDto> listTaskLists() {

             return taskListService.listTask().stream()
                .map(taskListMapper::toDto).
                toList();
    }

    @PostMapping("/create-task")
    public TaskLIstDto createTaskList(@RequestBody TaskLIstDto taskLIstDto)
    {
      TaskList createdTaskList = taskListService.createTaskList(
              taskListMapper.fromDto(taskLIstDto));

      return taskListMapper.toDto(createdTaskList);


    }
    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskLIstDto> getTaskList(@PathVariable("task_list_id")UUID taskListId) {
        return  taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }
    @PutMapping(path = "/{task_list_id}")
    public TaskLIstDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskLIstDto taskListDto
    ) {
        TaskList updatedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(updatedTaskList);
    }
    @DeleteMapping(path = "/{task_list_id}")
    public void  deleteTaskList(@PathVariable("task_list_id") UUID taskListId  ) {
        taskListService.deleteTaskId(taskListId);
    }


    }

