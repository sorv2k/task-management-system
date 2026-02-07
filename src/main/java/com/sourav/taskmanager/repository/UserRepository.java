package com.sourav.taskmanager.repository;

import com.sourav.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity with optimized database queries.
 * Includes indexed lookups for username and email for improved performance.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by username (uses index for performance)
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Find user by email (uses index for performance)
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Check if username exists
     */
    Boolean existsByUsername(String username);
    
    /**
     * Check if email exists
     */
    Boolean existsByEmail(String email);
    
    /**
     * Optimized query to fetch user with roles eagerly
     * This prevents N+1 query problems when accessing roles
     */
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findByUsernameWithRoles(String username);
}