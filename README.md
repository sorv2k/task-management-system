# Task Management System

A RESTful API backend for managing tasks with user authentication and role-based access control.

## What It Does

- Users can register and login with JWT authentication
- Create, update, and delete tasks
- Assign tasks to users with priority levels (LOW, MEDIUM, HIGH, URGENT)
- Track task status (TODO, IN_PROGRESS, COMPLETED, CANCELLED)
- View tasks by user, status, or due date
- Get task statistics and overdue tasks
- Admin users have full access to manage all users and tasks

## Technologies Used

- **Java 21** - Programming language
- **Spring Boot 3.2** - Backend framework
- **Spring Security + JWT** - Authentication and authorization
- **MySQL 8.0** - Database
- **Hibernate/JPA** - Database ORM
- **Docker** - Containerization
- **Maven** - Build tool

## Quick Start

1. Clone the repository
```bash
git clone https://github.com/sorv2k/task-management-system.git
cd task-management-system
```

2. Run with Docker
```bash
docker-compose up --build
```

3. API available at `http://localhost:8080`

## API Endpoints

**Public:**
- `POST /api/auth/signup` - Register
- `POST /api/auth/login` - Login

**Protected (requires JWT token):**
- `GET /api/tasks` - Get all tasks
- `POST /api/tasks` - Create task
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task (Admin only)
- `GET /api/tasks/stats` - Get statistics