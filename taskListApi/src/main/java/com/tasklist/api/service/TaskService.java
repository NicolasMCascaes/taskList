package com.tasklist.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasklist.api.dto.TaskRequestDto;
import com.tasklist.api.dto.TaskResponseDto;
import com.tasklist.api.entity.Task;
import com.tasklist.api.entity.TaskStatus;
import com.tasklist.api.exception.ResourceNotFoundException;
import com.tasklist.api.repository.TaskRepository;
import com.tasklist.api.repository.TaskUserRepository;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskUserRepository taskUserRepository;

    public TaskService(TaskRepository taskRepository, TaskUserRepository taskUserRepository) {
        this.taskRepository = taskRepository;
        this.taskUserRepository = taskUserRepository;
    }

    public void saveTask(TaskRequestDto dto) {
        if (!taskUserRepository.existsById(dto.userId())) {
            throw new ResourceNotFoundException("USER_NOT_FOUND");
        }
        Task task = new Task(null, dto.taskTitle(), dto.taskDescription(), TaskStatus.PENDING, LocalDateTime.now(),
                dto.userId());
        taskRepository.save(task);
    }

    public void cancelTask(String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("TASK_NOT_FOUND"));
        task.setTaskStatus(TaskStatus.CANCELED);
        taskRepository.save(task);

    }

    public void completeTask(String taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("TASK_NOT_FOUND"));
        task.setTaskStatus(TaskStatus.COMPLETED);
        taskRepository.save(task);
    }

    public List<TaskResponseDto> listAllByTaskStatus(TaskStatus taskStatus, String userId) {
        return taskRepository.findAllByTaskStatusAndUserId(taskStatus, userId).stream().map(Task::toDto).toList();
    }
}
