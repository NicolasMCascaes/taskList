package com.tasklist.api.dto;

import java.time.LocalDateTime;

import com.tasklist.api.entity.Task;
import com.tasklist.api.entity.TaskStatus;

public record TaskRequestDto(String taskTitle, String taskDescription,
        String userId) {
}
