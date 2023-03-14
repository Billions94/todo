package com.api.todo.Task;

import com.api.todo.SubTask.SubTask;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Task {
    @Id
    private String id;
    private String todoId;
    private String assignee;
    private String reporter;
    private String epic;
    private String summary;
    private String description;
    @DBRef
    private List<SubTask> subTask = new ArrayList<SubTask>();
    private Boolean isComplete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
