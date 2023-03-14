package com.api.todo.Task.Repository;

import com.api.todo.Task.Model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    Task getTaskById(String id);
    void deleteTaskByTodoId(String todoId);
}
