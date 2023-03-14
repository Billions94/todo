package com.api.todo.Todo.Repository;

import com.api.todo.Todo.Model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {
    Todo getTodoById(String id);
}
