package com.TaskTracker.TaskTracker.repositories;

import com.TaskTracker.TaskTracker.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepositories extends JpaRepository<TaskList, UUID> {
}
