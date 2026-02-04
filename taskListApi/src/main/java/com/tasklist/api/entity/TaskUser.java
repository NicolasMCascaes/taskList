package com.tasklist.api.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taskUser")
public class TaskUser {
    private String id;
    private String email;
    private String userPassword;
    private String username;

}
