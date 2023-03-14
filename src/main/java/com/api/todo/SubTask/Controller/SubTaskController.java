package com.api.todo.SubTask.Controller;

import com.api.todo.SubTask.Model.SubTask;
import com.api.todo.SubTask.Service.SubTaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SubTaskController {
    private final SubTaskService subTaskService;

    @GetMapping(value = "api/v1/subtasks")
    public List<SubTask> getSubTasks() {
        return subTaskService.getSubTasks();
    }

    @GetMapping(value = "api/v1/subtasks/{subTaskId}")
    public SubTask getSubTask(@PathVariable("subTaskId") String subTaskId) {
        return subTaskService.getSubTask(subTaskId);
    }

    @PostMapping(value = "api/v1/tasks/{taskId}/subtasks")
    public SubTask createSubTask(@PathVariable("taskId") String taskId, @RequestBody SubTask subTask) {
        return subTaskService.createSubTask(taskId, subTask);
    }

    @PatchMapping(value = "api/v1/subtasks/{subTaskId}")
    public SubTask updateSubTask(@PathVariable("subTaskId") String subTaskId, @RequestBody SubTask subTask) {
        return subTaskService.updateSubTask(
                subTaskId,
                subTask.getSummary(),
                subTask.getDescription(),
                subTask.getAssignee(),
                subTask.getReporter(),
                subTask.getIsComplete()
        );
    }

    @DeleteMapping(value = "api/v1/subtasks/{subTaskId}")
    public void deleteSubTask(@PathVariable("subTaskId") String subTaskId) {
        subTaskService.deleteSubTask(subTaskId);
    }
}
