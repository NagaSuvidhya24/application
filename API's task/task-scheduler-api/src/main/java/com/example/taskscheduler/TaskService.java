package com.example.taskscheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setStatus(TaskStatus.PENDING);
        return taskRepository.save(task);
    }

    public Task getTaskById(String taskId) {
        return taskRepository.findById(taskId).orElseThrow();
    }

    public Task updateTaskStatus(String taskId, TaskStatus status) {
        Task task = getTaskById(taskId);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task getNextTaskToProcess() {
        return taskRepository.findNextTaskToProcess().stream().findFirst().orElseThrow();
    }

    public List<Task> getPendingTasks(String sortBy, String order, Integer limit) {
        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        List<Task> tasks = taskRepository.findPendingTasks(sort);
        if (limit != null) {
            tasks = tasks.stream().limit(limit).collect(Collectors.toList());
        }
        return tasks;
    }
} 