package com.api.todo.Task.Controller;

import com.api.todo.Task.Service.TaskService;
import com.api.todo.Task.Model.Task;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping(value = "api/v1/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(value = "api/v1/tasks/{taskId}")
    public Task getTask(@PathVariable("taskId") String taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping(value = "api/v1/tasks/{epicName}")
    public List<Task> getTasksByEpicName(@PathVariable("epicName") String epicName) {
        return taskService.getListOfTasksByEpicName(epicName);
    }

    @PostMapping(value = "api/v1/todos/{todoId}/tasks")
    public Task createTask(@PathVariable("todoId") String todoId, @RequestBody Task task) {
        return taskService.createTask(task, todoId);
    }

    @PatchMapping(value = "api/v1/tasks/{taskId}")
    public Task updateTask(@PathVariable("taskId") String taskId, @RequestBody Task task) {
        return taskService.updateTask(
                taskId,
                task.getSummary(),
                task.getDescription(),
                task.getEpic(),
                task.getAssignee(),
                task.getReporter(),
                task.getIsComplete()
        );
    }

    @DeleteMapping(value = "api/v1/tasks/{taskId}")
    public void deleteTask(@PathVariable("taskId") String taskId) {
        taskService.deleteTask(taskId);
    }

}
