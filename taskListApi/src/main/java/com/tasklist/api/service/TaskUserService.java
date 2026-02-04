package com.tasklist.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tasklist.api.dto.UserRegisterRequestDto;
import com.tasklist.api.entity.TaskUser;
import com.tasklist.api.exception.DataConflictException;
import com.tasklist.api.repository.TaskUserRepository;

@Service
public class TaskUserService {

    private final PasswordEncoder passwordEncoder;
    private final TaskUserRepository taskUserRepository;

    public TaskUserService(TaskUserRepository taskUserRepository, PasswordEncoder passwordEncoder) {
        this.taskUserRepository = taskUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRegisterRequestDto dto) {
        if (taskUserRepository.existsByEmail(dto.email())) {
            throw new DataConflictException("USER_ALREADY_EXISTS");
        }
        TaskUser user = new TaskUser(null, dto.email(), dto.password(), dto.username(), null);
        user.setUserPassword(passwordEncoder.encode(dto.password()));
        taskUserRepository.save(user);
    }
}
