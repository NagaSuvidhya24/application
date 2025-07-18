package com.example.taskscheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable String taskId) {
        return taskService.getTaskById(taskId);
    }

    @PatchMapping("/{taskId}/status")
    public Task updateTaskStatus(@PathVariable String taskId, @RequestBody TaskStatus status) {
        return taskService.updateTaskStatus(taskId, status);
    }

    @GetMapping("/next")
    public Task getNextTaskToProcess() {
        return taskService.getNextTaskToProcess();
    }

    @GetMapping("/pending")
    public List<Task> getPendingTasks(
        @RequestParam(required = false) String sortBy,
        @RequestParam(required = false) String order,
        @RequestParam(required = false) Integer limit
    ) {
        return taskService.getPendingTasks(sortBy, order, limit);
    }
} 