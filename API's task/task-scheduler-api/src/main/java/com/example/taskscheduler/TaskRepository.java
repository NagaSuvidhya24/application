package com.example.taskscheduler;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    @Query("SELECT t FROM Task t WHERE t.status = 'PENDING' ORDER BY t.estimatedTimeMinutes ASC, t.submissionTimestamp ASC")
    List<Task> findNextTaskToProcess();

    @Query("SELECT t FROM Task t WHERE t.status = 'PENDING'")
    List<Task> findPendingTasks(Sort sort);
} 