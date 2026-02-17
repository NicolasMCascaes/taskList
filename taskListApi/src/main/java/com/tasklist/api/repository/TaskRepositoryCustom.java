package com.tasklist.api.repository;

import java.util.Map;

import com.tasklist.api.entity.TaskStatus;

public interface TaskRepositoryCustom {
    Map<TaskStatus, Long> countTasksByStatus(String userId);
}
