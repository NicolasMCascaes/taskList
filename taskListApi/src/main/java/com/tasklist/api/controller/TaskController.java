package com.tasklist.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasklist.api.dto.TaskRequestDto;
import com.tasklist.api.dto.TaskResponseDto;
import com.tasklist.api.entity.TaskStatus;
import com.tasklist.api.service.TaskService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/test")
    public String getMethodName() {
        return new String("Testeeeeeee");
    }

    @GetMapping("/listAllTasksByUserIdAndStatus")
    public ResponseEntity<List<TaskResponseDto>> listAllTasksByUserIdAndStatus(@RequestParam TaskStatus status,
            @RequestParam String userId) {
        return ResponseEntity.ok(taskService.listAllByTaskStatus(status, userId));
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveTask(@RequestBody TaskRequestDto dto) {
        taskService.saveTask(dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/cancel")
    public ResponseEntity<Void> cancelTask(@PathVariable String taskId) {
        taskService.cancelTask(taskId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/complete")
    public ResponseEntity<Void> completeTask(@PathVariable String taskId) {
        taskService.completeTask(taskId);
        return ResponseEntity.ok().build();
    }

}
