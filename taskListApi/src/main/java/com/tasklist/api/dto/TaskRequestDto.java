package com.tasklist.api.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDto(@NotBlank(message = "Task title is mandatory") String taskTitle, String taskDescription,
                @NotBlank(message = "UserId can not be blank") String userId) {
}
