package com.tasklist.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tasklist.api.entity.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

}
