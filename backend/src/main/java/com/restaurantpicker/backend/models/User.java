package com.restaurantpicker.backend.models;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    @Column(name="auth_type")
    private String auth_type; 

    @Column(name="username")
    private String username;
    
    @Column(name="email")
    private String email; 

    @Column(name="password")
    private String password; 

    @Column(name="google_id")
    private String google_id;

    public User() {}

    public User(int id, String auth_type, String username, String email, Optional<String> password, Optional<String> google_id) {
        this.id = id; 
        this.auth_type = auth_type; 
        this.username = username;
        this.email = email; 
        this.password = password.get(); 
        this.google_id = google_id.get(); 
    }

    // we only have a setter method for the username in case the user wants to change it 
    public void setUsername(String newUsername) {
        this.username = newUsername; 
        return; 
    }

    public int getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email; 
    }

    public String getAuthType() {
        return this.auth_type; 
    }

    public String getUsername() {
        return this.username; 
    }

    public String getPassword() {
        return this.password; 
    }

    public String getGoogleId() {
        return this.google_id; 
    }
}
