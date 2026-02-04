package com.tasklist.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasklist.api.dto.UserLoginRequestDto;
import com.tasklist.api.dto.UserRegisterRequestDto;
import com.tasklist.api.service.TaskUserService;
import com.tasklist.api.service.auth.UserAuthService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final TaskUserService taskUserService;
    private final UserAuthService userAuthService;

    public AuthController(TaskUserService taskUserService, UserAuthService userAuthService) {
        this.taskUserService = taskUserService;
        this.userAuthService = userAuthService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequestDto dto) {
        return ResponseEntity.ok(userAuthService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRegisterRequestDto dto) {
        taskUserService.registerUser(dto);

        return ResponseEntity.ok().build();
    }

}
