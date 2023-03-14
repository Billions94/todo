package com.api.todo.SubTask;

import com.api.todo.Task.Task;
import com.api.todo.Task.TaskRepository;
import com.api.todo.Todo.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;
    private final TodoRepository todoRepository;

    public List<SubTask> getSubTasks() {
        return subTaskRepository.findAll();
    }

    public SubTask getSubTask(String subTaskId) {
        return subTaskRepository.getSubTaskById(subTaskId);
    }

    public SubTask createSubTask(String taskId, SubTask subTask) {
        Task task = taskRepository.getTaskById(taskId);

        subTask.setTaskId(taskId);
        subTask.setTodoId(task.getTodoId());
        subTask.setCreatedAt(LocalDateTime.now());

        task.getSubTask().add(subTask);
        subTaskRepository.save(subTask);
        taskRepository.save(task);

        return subTask;
    }

    public SubTask updateSubTask(
            String subTaskId,
            String summary,
            String description,
            String assignee,
            String reporter,
            Boolean isComplete
    ) {
        SubTask subTask = subTaskRepository.getSubTaskById(subTaskId);
        Task task = taskRepository.getTaskById(subTask.getTaskId());

        if (summary != null && summary.length() > 0 && !Objects.equals(subTask.getSummary(), summary))
            subTask.setSummary(summary);

        if (description != null && description.length() > 0 && !Objects.equals(subTask.getDescription(), description))
            subTask.setDescription(description);

        if (assignee != null && assignee.length() > 0 && !Objects.equals(subTask.getAssignee(), assignee))
            subTask.setDescription(assignee);

        if (reporter != null && reporter.length() > 0 && !Objects.equals(subTask.getReporter(), reporter))
            subTask.setDescription(reporter);

        if (isComplete != null && !Objects.equals(subTask.getIsComplete(), isComplete))
            subTask.setIsComplete(isComplete);

        subTask.setUpdatedAt(LocalDateTime.now());
        subTaskRepository.save(subTask);
        taskRepository.save(task);

        return subTask;
    }

    public void deleteSubTask(String subTaskId) {
        boolean exists = todoRepository.existsById(subTaskId);

        if (!exists)
            throw new IllegalStateException("SubTask with id" + subTaskId + "does not exists");

        subTaskRepository.deleteById(subTaskId);
    }
}
