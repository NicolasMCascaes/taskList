package com.tasklist.api.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tasklist.api.dto.TaskResponseDto;

@Document(collection = "task")
public class Task {
    @Id
    private String id;
    private String taskTitle;
    private String taskDescription;
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;
    String userId;

    public Task(String id, String taskTitle, String taskDescription, TaskStatus taskStatus, LocalDateTime createdAt,
            String userId) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public TaskResponseDto toDto() {
        return new TaskResponseDto(id, taskTitle, taskDescription, taskStatus, createdAt, userId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
