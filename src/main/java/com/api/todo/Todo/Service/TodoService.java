package com.api.todo.Todo.Service;

import com.api.todo.SubTask.Repository.SubTaskRepository;
import com.api.todo.Task.Repository.TaskRepository;
import com.api.todo.Todo.Model.Todo;
import com.api.todo.Todo.Repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TaskRepository taskRepository;
    private final SubTaskRepository subTaskRepository;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodo(String todoId) {
        return todoRepository.getTodoById(todoId);
    }

    public Todo addNewTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public Todo updateTodo(String todoId, String title, Boolean isComplete) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalStateException(
                "Todo with id" + todoId + "does not exists"
        ));

        if (title != null && title.length() > 0 && !Objects.equals(todo.getTitle(), title))
            todo.setTitle(title);

        if (isComplete != null && !Objects.equals(todo.getIsComplete(), isComplete))
            todo.setIsComplete(isComplete);

        todo.setUpdatedAt(LocalDateTime.now());
        todoRepository.save(todo);

        return todo;
    }

    public void removeTodo(String todoId) {
        boolean exists = todoRepository.existsById(todoId);

        if (!exists)
            throw new IllegalStateException("Todo with id" + todoId + "does not exists");

        subTaskRepository.deleteAllSubTaskByTodoId(todoId);
        taskRepository.deleteTaskByTodoId(todoId);
        todoRepository.deleteById(todoId);
    }
}
