package com.tasklist.api.dto;

import com.tasklist.api.entity.TaskUser;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDto(@NotBlank(message = "Email is mandatory") String email,
        @NotBlank(message = "Password is mandatory") String password,
        @NotBlank(message = "Username is mandatory") String username) {
    public TaskUser toEntity() {
        return new TaskUser(null, email, password, username, null);
    }
}
