package com.api.todo.Todo;

import com.api.todo.Task.Task;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Document
public class Todo {
    @Id
    private String id;
    private String title;
    @DBRef
    private List<Task> task = new ArrayList<Task>();
    private Boolean isComplete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
