package com.api.todo.Task;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    Task getTaskById(String id);
    void deleteTaskByTodoId(String todoId);
}
