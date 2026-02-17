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
        // 1️⃣ Filtra as tasks pelo ID do usuário
        MatchOperation matchUser = Aggregation.match(Criteria.where("userId").is(userId));
        System.out.println("Isto: " + matchUser);

        // 2️⃣ Agrupa por status e conta quantas há em cada grupo
        GroupOperation groupByStatus = Aggregation.group("taskStatus").count().as("count");
        System.out.println("Isto: " + groupByStatus);

        // 3️⃣ Monta a pipeline com os dois estágios
        Aggregation aggregation = Aggregation.newAggregation(matchUser, groupByStatus);
        System.out.println("Isto: " + aggregation);
        // 4️⃣ Executa a agregação
        AggregationResults<Result> results = mongoTemplate.aggregate(
                aggregation, Task.class, Result.class);
        System.out.println("Isto: " + results);
        // 5️⃣ Converte o resultado em um Map
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
