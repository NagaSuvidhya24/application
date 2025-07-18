package com.example.taskscheduler;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    private String taskId;

    private String description;

    private int estimatedTimeMinutes;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Timestamp submissionTimestamp;

    // Getters and setters
} 