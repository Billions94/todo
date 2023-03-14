package com.api.todo.SubTask.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class SubTask {
    @Id
    private String id;
    private String taskId;
    private String todoId;
    private String assignee;
    private String reporter;
    private String summary;
    private String description;
    private Boolean isComplete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
