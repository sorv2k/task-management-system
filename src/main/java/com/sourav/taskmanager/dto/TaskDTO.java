package com.sourav.taskmanager.dto;

import com.sourav.taskmanager.model.Task.TaskPriority;
import com.sourav.taskmanager.model.Task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Data Transfer Objects for Task API operations
 */
public class TaskDTO {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateTaskRequest {
        @NotBlank
        @Size(min = 3, max = 100)
        private String title;
        
        @Size(max = 1000)
        private String description;
        
        private TaskPriority priority;
        private Long assignedToId;
        private LocalDateTime dueDate;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateTaskRequest {
        private String title;
        private String description;
        private TaskStatus status;
        private TaskPriority priority;
        private Long assignedToId;
        private LocalDateTime dueDate;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskResponse {
        private Long id;
        private String title;
        private String description;
        private TaskStatus status;
        private TaskPriority priority;
        private UserInfo assignedTo;
        private UserInfo createdBy;
        private LocalDateTime dueDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {
        private Long id;
        private String username;
        private String email;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskStatsResponse {
        private Long totalTasks;
        private Long todoTasks;
        private Long inProgressTasks;
        private Long completedTasks;
        private Long overdueTasksCount;
    }
}