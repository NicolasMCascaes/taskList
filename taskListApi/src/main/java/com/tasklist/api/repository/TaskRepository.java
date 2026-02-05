package com.tasklist.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tasklist.api.entity.Task;
import com.tasklist.api.entity.TaskStatus;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findAllByTaskStatusAndUserId(TaskStatus status, String userId);
}
