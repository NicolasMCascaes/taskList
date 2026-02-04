package com.tasklist.api.service.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tasklist.api.dto.UserLoginRequestDto;
import com.tasklist.api.entity.TaskUser;
import com.tasklist.api.exception.ResourceNotFoundException;
import com.tasklist.api.repository.TaskUserRepository;
import com.tasklist.api.security.jwt.JwtUtil;

@Service
public class UserAuthService implements UserDetailsService {
    private final TaskUserRepository taskUserRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserAuthService(TaskUserRepository taskUserRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.taskUserRepository = taskUserRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return taskUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for this email!"));
    }

    public Map<String, String> login(UserLoginRequestDto dto) {
        Map<String, String> response = new HashMap<>();
        TaskUser taskUser = taskUserRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ResourceNotFoundException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(dto.password(), taskUser.getPassword())) {
            throw new BadCredentialsException("INVALID_CREDENCIALS");
        }
        response.put("token", jwtUtil.generateToken(taskUser));
        return response;

    }

}
