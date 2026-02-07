package com.sourav.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Task Management System application.
 * This Spring Boot application provides RESTful APIs for task management
 * with authentication, authorization, and MySQL persistence.
 */
@SpringBootApplication
public class TaskManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TaskManagementApplication.class, args);
    }
}