package com.example.homework13q1.TaskTracker;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskTracker {
    private String id;
    private String title;
    private String description;
    private String status;
}
