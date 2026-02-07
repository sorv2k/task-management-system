package com.sourav.taskmanager.controller;

import com.sourav.taskmanager.dto.TaskDTO;
import com.sourav.taskmanager.model.Task;
import com.sourav.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * REST Controller for task management operations
 * Provides CRUD operations for tasks with role-based access control
 */
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    /**
     * POST /api/tasks - Create a new task
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TaskDTO.TaskResponse> createTask(
            @Valid @RequestBody TaskDTO.CreateTaskRequest request) {
        TaskDTO.TaskResponse response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * GET /api/tasks - Get all tasks
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TaskDTO.TaskResponse>> getAllTasks() {
        List<TaskDTO.TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    /**
     * GET /api/tasks/{id} - Get task by ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TaskDTO.TaskResponse> getTaskById(@PathVariable Long id) {
        TaskDTO.TaskResponse task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    
    /**
     * GET /api/tasks/user/{userId} - Get tasks assigned to a user
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TaskDTO.TaskResponse>> getTasksByUser(@PathVariable Long userId) {
        List<TaskDTO.TaskResponse> tasks = taskService.getTasksByAssignedUser(userId);
        return ResponseEntity.ok(tasks);
    }
    
    /**
     * GET /api/tasks/status/{status} - Get tasks by status
     */
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TaskDTO.TaskResponse>> getTasksByStatus(@PathVariable Task.TaskStatus status) {
        List<TaskDTO.TaskResponse> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }
    
    /**
     * GET /api/tasks/overdue - Get overdue tasks
     */
    @GetMapping("/overdue")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TaskDTO.TaskResponse>> getOverdueTasks() {
        List<TaskDTO.TaskResponse> tasks = taskService.getOverdueTasks();
        return ResponseEntity.ok(tasks);
    }
    
    /**
     * GET /api/tasks/stats - Get task statistics
     */
    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TaskDTO.TaskStatsResponse> getTaskStats() {
        TaskDTO.TaskStatsResponse stats = taskService.getTaskStats();
        return ResponseEntity.ok(stats);
    }
    
    /**
     * PUT /api/tasks/{id} - Update a task
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TaskDTO.TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskDTO.UpdateTaskRequest request) {
        TaskDTO.TaskResponse response = taskService.updateTask(id, request);
        return ResponseEntity.ok(response);
    }
    
    /**
     * DELETE /api/tasks/{id} - Delete a task
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().body("Task deleted successfully");
    }
}