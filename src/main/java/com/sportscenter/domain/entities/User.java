package com.sportscenter.domain.entities;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;

    public User() {}

    public User(int id) {
        this.id = id;
    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + 
               ", role=" + role + ", active=" + active + "]";
    }
}