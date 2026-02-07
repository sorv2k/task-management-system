package com.sourav.taskmanager.controller;

import com.sourav.taskmanager.dto.UserDTO;
import com.sourav.taskmanager.model.User;
import com.sourav.taskmanager.security.JwtUtils;
import com.sourav.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * REST Controller for authentication endpoints
 * Handles user registration and login with JWT token generation
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    /**
     * POST /api/auth/login - Authenticate user and return JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDTO.LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), 
                loginRequest.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        org.springframework.security.core.userdetails.User userDetails = 
            (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", ""))
                .collect(Collectors.toSet());
        
        User user = userService.getUserByUsername(userDetails.getUsername());
        
        UserDTO.JwtResponse response = UserDTO.JwtResponse.builder()
                .token(jwt)
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles)
                .build();
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * POST /api/auth/signup - Register a new user
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO.SignupRequest signupRequest) {
        try {
            userService.registerUser(signupRequest);
            return ResponseEntity.ok(new UserDTO.MessageResponse("User registered successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new UserDTO.MessageResponse("Error: " + e.getMessage()));
        }
    }
}