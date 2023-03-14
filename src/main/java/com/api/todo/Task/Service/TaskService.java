package com.api.todo.Task.Service;

import com.api.todo.SubTask.Repository.SubTaskRepository;
import com.api.todo.Task.Repository.TaskRepository;
import com.api.todo.Task.Model.Task;
import com.api.todo.Todo.Model.Todo;
import com.api.todo.Todo.Repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TodoRepository todoRepository;
    private final SubTaskRepository subTaskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(String taskId) {
        return taskRepository.getTaskById(taskId);
    }

    public Task createTask(Task task, String todoId) {
        Todo todo = todoRepository.getTodoById(todoId);

        task.setCreatedAt(LocalDateTime.now());
        task.setTodoId(todoId);

        todo.getTask().add(task);
        taskRepository.save(task);
        todoRepository.save(todo);

        return task;
    }

    public Task updateTask(
            String taskId,
            String summary,
            String description,
            String epic,
            String assignee,
            String reporter,
            Boolean isComplete
    ) {
        Task task = taskRepository.getTaskById(taskId);
        Todo todo = todoRepository.getTodoById(task.getTodoId());

        if (summary != null && summary.length() > 0 && !Objects.equals(task.getSummary(), summary))
            task.setSummary(summary);

        if (description != null && description.length() > 0 && !Objects.equals(task.getDescription(), description))
            task.setDescription(description);

        if (epic != null && epic.length() > 0 && !Objects.equals(task.getEpic(), epic))
            task.setEpic(epic);

        if (assignee != null && assignee.length() > 0 && !Objects.equals(task.getAssignee(), assignee))
            task.setDescription(assignee);

        if (reporter != null && reporter.length() > 0 && !Objects.equals(task.getReporter(), reporter))
            task.setDescription(reporter);

        if (isComplete != null && !Objects.equals(task.getIsComplete(), isComplete))
            task.setIsComplete(isComplete);

        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);
        todoRepository.save(todo);

        return task;
    }

    public void deleteTask(String taskId) {
        boolean exists = todoRepository.existsById(taskId);

        if (!exists)
            throw new IllegalStateException("Task with id" + taskId + "does not exists");

        subTaskRepository.deleteAllSubTaskByTaskId(taskId);
        taskRepository.deleteById(taskId);
    }
}
