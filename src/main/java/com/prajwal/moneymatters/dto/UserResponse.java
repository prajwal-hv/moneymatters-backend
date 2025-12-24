package com.prajwal.moneymatters.dto;

import com.prajwal.moneymatters.Model.UserRole;

import java.time.Instant;

public class UserResponse {
    private Long id;
    private String userName;
    private String email;
    private UserRole role;
    private Instant createdAt;

    public UserResponse(Long id, String userName, String email, UserRole role, Instant createdAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    // getters
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getEmail() { return email; }
    public UserRole getRole() { return role; }
    public Instant getCreatedAt() { return createdAt; }
}
