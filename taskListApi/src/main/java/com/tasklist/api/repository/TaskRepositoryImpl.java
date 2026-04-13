package com.tasklist.api.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.tasklist.api.entity.Task;
import com.tasklist.api.entity.TaskStatus;

public class TaskRepositoryImpl implements TaskRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public TaskRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Map<TaskStatus, Long> countTasksByStatus(String userId) {

        MatchOperation matchUser = Aggregation.match(Criteria.where("userId").is(userId));

        GroupOperation groupByStatus = Aggregation.group("taskStatus").count().as("count");

        Aggregation aggregation = Aggregation.newAggregation(matchUser, groupByStatus);

        AggregationResults<Result> results = mongoTemplate.aggregate(
                aggregation, Task.class, Result.class);

        Map<TaskStatus, Long> counts = new HashMap<>();
        for (Result r : results.getMappedResults()) {
            TaskStatus status = TaskStatus.valueOf(r.getId());
            counts.put(status, r.getCount());
        }

        return counts;
    }

    private static class Result {
        private String _id;
        private Long count;

        public String getId() {
            return _id;
        }

        public long getCount() {
            return count;
        }
    }
}
