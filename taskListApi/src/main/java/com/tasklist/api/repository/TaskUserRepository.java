package com.tasklist.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tasklist.api.entity.TaskUser;

@Repository
public interface TaskUserRepository extends MongoRepository<TaskUser, String> {
    Optional<TaskUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
