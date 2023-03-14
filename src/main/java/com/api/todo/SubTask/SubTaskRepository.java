package com.api.todo.SubTask;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubTaskRepository extends MongoRepository<SubTask, String> {
    SubTask getSubTaskById(String id);
    void deleteAllSubTaskByTodoId(String todoId);
    void deleteAllSubTaskByTaskId(String taskId);
}
