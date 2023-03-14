package com.api.todo.Todo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {
    Todo getTodoById(String id);
}
