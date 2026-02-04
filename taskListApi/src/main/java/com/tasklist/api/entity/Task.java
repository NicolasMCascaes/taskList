package com.tasklist.api.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task")
public class Task {
    @Id
    private String id;
    private String taskTitle;
    private String taskDescription;
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;

}
