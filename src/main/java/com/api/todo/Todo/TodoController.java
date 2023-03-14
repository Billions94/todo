package com.api.todo.Todo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping(value = "/api/v1/todos")
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @GetMapping(value = "/api/v1/todos/{todoId}")
    public Todo getTodo(@PathVariable("todoId") String todoId) {
        return todoService.getTodo(todoId);
    }

    @PostMapping(value = "/api/v1/todos")
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.addNewTodo(todo);
    }

    @PatchMapping(value = "/api/v1/todos/{todoId}")
    public Todo updateTodo(@PathVariable("todoId") String todoId, @RequestBody Todo todo) {
        return todoService.updateTodo(
                todoId,
                todo.getTitle(),
                todo.getIsComplete()
        );
    }

    @DeleteMapping(value = "/api/v1/todos/{todoId}")
    public void deleteTodo(@PathVariable("todoId") String todoId) {
        todoService.removeTodo(todoId);
    }
}

