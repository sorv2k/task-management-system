package com.sourav.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

/**
 * Data Transfer Objects for API requests and responses
 */
public class UserDTO {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignupRequest {
        @NotBlank
        @Size(min = 3, max = 50)
        private String username;
        
        @NotBlank
        @Size(max = 100)
        @Email
        private String email;
        
        @NotBlank
        @Size(min = 6, max = 40)
        private String password;
        
        private Set<String> roles;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        @NotBlank
        private String username;
        
        @NotBlank
        private String password;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JwtResponse {
        private String token;
        @Builder.Default
        private String type = "Bearer";
        private Long id;
        private String username;
        private String email;
        private Set<String> roles;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageResponse {
        private String message;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private Long id;
        private String username;
        private String email;
        private Set<String> roles;
    }
}