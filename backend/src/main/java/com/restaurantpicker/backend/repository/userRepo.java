package com.restaurantpicker.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurantpicker.backend.models.User;

@Repository
public interface userRepo extends JpaRepository<User, Integer> {
    @Query(value="SELECT * FROM users WHERE id=?1", nativeQuery = true)
    public User getUserById(String id); 
}