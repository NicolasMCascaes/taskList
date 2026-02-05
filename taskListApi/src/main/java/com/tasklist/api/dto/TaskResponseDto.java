package com.tasklist.api.dto;

import java.time.LocalDateTime;

import com.tasklist.api.entity.TaskStatus;

public record TaskResponseDto(String taskId, String taskTitle, String taskDescription, TaskStatus status,
        LocalDateTime createdAt,
        String userId) {

}
